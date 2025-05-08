package com.major.SkillsSwapCommunity.service;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.major.SkillsSwapCommunity.entity.ApiResponse;
import com.major.SkillsSwapCommunity.entity.OtpDetails;
import com.major.SkillsSwapCommunity.entity.UserDetails;
import com.major.SkillsSwapCommunity.entity.loginDto;
import com.major.SkillsSwapCommunity.jwtUtils.jwtUtils;
import com.major.SkillsSwapCommunity.passwordUtils.passwordUtils;
import com.major.SkillsSwapCommunity.repository.authRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class authService {

    @Autowired
    private authRepo AuthRepo;

    @Autowired
    private emailService EmailService;

    @Autowired
    private passwordUtils PasswordUtils;

    @Autowired
            private jwtUtils JwtUtils;

    Map<String, OtpDetails> otpStore = new HashMap<>();

    public ResponseEntity<?> signupToOtp(UserDetails signupRequest){
        try{
            String userEmail = signupRequest.getEmail();
            if(AuthRepo.existsByEmailIgnoreCase(signupRequest.getEmail())){
                System.out.println("user present hai");
                throw new RuntimeException("user already exits");
            }else{
                return generateOtp(userEmail);
            }
        }catch (Exception e){
            return ResponseEntity.ok(new ApiResponse<>(false,e.getMessage(),null));
        }
    }

    public ResponseEntity<?> generateOtp(String email){
        System.out.println("comes for otp " + email);
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // 6-digit OTP
        System.out.println(otp);
        // Save OTP with email
        otpStore.put(email, new OtpDetails(otp, System.currentTimeMillis()));

        // Send OTP via email
        EmailService.sendEmail(email, "Your OTP Code", "Your OTP is: " + otp);

        return ResponseEntity.ok(new ApiResponse<>(true,"Otp Sent Successfully",null));
    }


    public ResponseEntity<?> signup(UserDetails signupRequest){


        String hashPassword = PasswordUtils.hashPassword(signupRequest.getPassword());
        UserDetails user = new UserDetails(
                new ObjectId(), signupRequest.getName(),signupRequest.getEmail(),
                signupRequest.getContact(),signupRequest.getSkills(),hashPassword);

        AuthRepo.save(user);

        return ResponseEntity.ok(new ApiResponse<>(true,"Signup Successfully",null));
    }


    public ResponseEntity<?> verifyOtp(int enteredOtp,UserDetails signupRequest) {
//        System.out.println("Step6");
        String email = signupRequest.getEmail();
//        System.out.println( enteredOtp);

        OtpDetails otpDetails = otpStore.get(email);
//        System.out.println(email);
//        System.out.println(otpDetails);
        if (otpDetails == null) {
            return ResponseEntity.ok(new ApiResponse<>(false,"Otp Is Null",null));
        }

        long currentTime = System.currentTimeMillis();
        if (currentTime - otpDetails.getTimestamp() > 5 * 60 * 1000) { // 5 min expiry
            return ResponseEntity.ok(new ApiResponse<>(false,"Otp Expire",null));
        }

        if (otpDetails.getOtp() != enteredOtp) {
            return ResponseEntity.ok(new ApiResponse<>(false,"Otp Is Invalid",null));
        }
        otpStore.clear();
        // OTP verified – now save user in DB
        if(!checkForEmptyField(signupRequest)){
            return ResponseEntity.ok(new ApiResponse<>(false,"please enter all details",null));
        }
        ResponseEntity<?> signupResponse = signup(signupRequest);
        return ResponseEntity.ok(new ApiResponse<>(true,"signup Successfully",signupResponse));
    }

    public Boolean checkForEmptyField(UserDetails signUpRequest){
        if(signUpRequest.getEmail().isEmpty() ||
            signUpRequest.getContact().isEmpty() ||
               signUpRequest.getPassword().isEmpty() ||
                  signUpRequest.getName().isEmpty() ||
                    signUpRequest.getSkills().isEmpty()){
            return false;
        }
        return true;
    }


    public ResponseEntity<?>login(loginDto loginDto){

        try{
            UserDetails user = AuthRepo.findByEmailIgnoreCase(loginDto.getEmail())
                    .orElseThrow(() -> new RuntimeException("user not found"));

            if(!PasswordUtils.matchPassword(loginDto.getPassword(),user.getPassword())){
                throw new RuntimeException("Invalid Credentials");
            }
            String jwt = JwtUtils.generateToken(loginDto.getEmail());
            return ResponseEntity.ok(new ApiResponse<>(true,"Login Successfully",jwt));

        } catch (RuntimeException e) {
            return ResponseEntity.ok(new ApiResponse<>(false,e.getMessage(),null));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new ApiResponse<>(false,"Something went wrong",e));
        }

    }
}

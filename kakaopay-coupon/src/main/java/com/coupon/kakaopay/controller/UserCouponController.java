package com.coupon.kakaopay.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coupon.kakaopay.model.dto.UserCoupon;
import com.coupon.kakaopay.model.request.CouponPayment;
import com.coupon.kakaopay.service.UserCouponService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/users")
public class UserCouponController {

	@Autowired
	private UserCouponService userCouponService;

	/**
	 * One of the generated coupons is paid to the user.
	 * 
	 */
	@PostMapping(value = "/{userId}/coupon")
	public UserCoupon createUserCoupon(@PathVariable Long userId) {
		log.info("## UserCouponController - createUserCoupon(user_serial : {})", userId);
		return userCouponService.createUserCoupon(userId);
	}

	/**
	 * Search for coupons paid to users.
	 * 
	 */
	@GetMapping(value = "/{userId}/coupons")
	public List<UserCoupon> getUserCoupons(@PathVariable Long userId) {
		log.info("## UserCouponController - getUserCoupons(user_serial : {})", userId);
		return userCouponService.getUserCouponList(userId);
	}

	/**
	 * Use one of the coupons paid.
	 * 
	 * Cancel one of the coupons paid. (Reusable)
	 * 
	 */
	@PatchMapping(value = "/{userId}/coupon/status")
	public void updateCouponStatus(@PathVariable Long userId, @RequestBody @Valid CouponPayment couponPayment) {
		log.info("## UserCouponController - updateCouponStatus(user_serial : {}, CouponPayment : {})", userId,
				couponPayment);
		userCouponService.updateCouponPayment(userId, couponPayment);
	}

}

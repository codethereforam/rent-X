package priv.thinkam.rentx.web.service.param;

import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;
import priv.thinkam.rentx.web.service.validator.auth.AuthValidatorGroup;

import javax.validation.constraints.*;

/**
 * register param
 *
 * @author yanganyu
 * @date 2019/3/3 21:44
 */
@Data
@Accessors(chain = true)
public class RegisterParam {
	/**
	 * 用户名
	 */
	@NotEmpty(message = "此处不能留空", groups = AuthValidatorGroup.Register.class)
	@Size(min = 5, max = 15, message = "长度应为5-15个字符，请勿包含姓名/身份证/银行卡等隐私信息", groups = AuthValidatorGroup.Register.class)
	@Pattern(regexp = "[0-9a-zA-Z\u4e00-\u9fa5_]+", message = "用户名仅支持中英文、数字和下划线", groups = AuthValidatorGroup.Register.class)
	private String username;

	/**
	 * 邮箱
	 */
	@NotEmpty(message = "此处不能留空", groups = {AuthValidatorGroup.Register.class, AuthValidatorGroup.SendEmailCaptcha.class})
	@Size(max = 50, message = "邮箱长度不能超过50",
			groups = {AuthValidatorGroup.Register.class, AuthValidatorGroup.SendEmailCaptcha.class})
	@Email(message = "邮箱格式不符合要求", groups = {AuthValidatorGroup.Register.class, AuthValidatorGroup.SendEmailCaptcha.class})
	private String email;

	/**
	 * 性别(0:女，1:男，2:不愿透露)
	 */
	@NotNull(message = "此处不能留空", groups = AuthValidatorGroup.Register.class)
	@Range(min = 0, max = 2, message = "请选择给定的性别", groups = AuthValidatorGroup.Register.class)
	private Integer sex;

	/**
	 * 密码
	 */
	@NotBlank(message = "此处不能留空", groups = AuthValidatorGroup.Register.class)
	@Size(min = 6, max = 16, message = "长度应为6-16个字符", groups = {AuthValidatorGroup.Register.class, AuthValidatorGroup.ResetPassword.class})
	@Pattern(regexp = "[0-9a-zA-Z\\p{Punct} ”]+", message = "密码仅支持字母、数字及标点符号", groups = AuthValidatorGroup.Register.class)
	private String password;

	/**
	 * 确认密码
	 */
	@NotBlank(message = "此处不能留空", groups = {AuthValidatorGroup.Register.class, AuthValidatorGroup.ResetPassword.class})
	private String confirmedPassword;

	/**
	 * 邮箱验证码
	 */
	@NotEmpty(message = "此处不能留空", groups = AuthValidatorGroup.Register.class)
	private String emailCaptcha;

	/**
	 * 角色(2:出租人，3:承租人)
	 */
	@NotNull(message = "此处不能留空", groups = AuthValidatorGroup.Register.class)
	@Range(min = 2, max = 3, message = "请选择给定的性别", groups = AuthValidatorGroup.Register.class)
	private Integer role;
}


<#include "${full_templates_path}/init.ftl" />

<#assign
		sign_in_text = languageUtil.get(locale, "sign-in")
		sign_in_url = htmlUtil.escape(theme_display.getURLSignIn())
/>

<#assign
		sign_out_text = languageUtil.get(locale, "sign-out")
		sign_out_url = htmlUtil.escape(theme_display.getURLSignOut())
/>

<div class="top-nav">
	<div class="container">
		<div class="row">
			<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
				<div class="left-top-nav">
					<ul class="list-inline mb-0">
						<li class="list-inline-item py-2">
							<a href="#"> <strong>Call On : </strong> +91 98XXXXXXXX </a>
						</li>
						<li class="list-inline-item py-2">
							<a href="#"> <strong>Email On : </strong> info@HrmsPortal.com </a>
						</li>
					</ul>
				</div>
			</div>
			
			<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
				<div class="right-top-nav text-right">
					<ul class="list-inline mb-0">
						<li class="list-inline-item py-2">
							<a href="javascript:void(0)"> <strong> Welcome : </strong> </a>
						</li>
						<#if is_signed_in>
							<li class="list-inline-item py-2">
								<a href="javascript:void(0)"> ${user.getFullName()} </a>
							</li>
							<li class="list-inline-item py-2">
								<a href="${sign_out_url}"> ${sign_out_text} </a>
							</li>
						<#else>
							<li class="list-inline-item py-2">
								<a href="javascript:void(0)"> Guest User </a>
							</li>
							<li class="list-inline-item py-2">
								<a data-redirect="${is_login_redirect_required?string}" href="${sign_in_url}" id="sign-in" rel="nofollow"> ${sign_in_text} </a>
							</li>
						</#if>	
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
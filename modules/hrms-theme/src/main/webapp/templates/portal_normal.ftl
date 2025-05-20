<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">

<head>
	<title>${html_title}</title>

	<meta content="initial-scale=1.0, width=device-width" name="viewport" />

	<@liferay_util["include"] page=top_head_include />
</head>

<body class="${css_class}">

<@liferay_ui["quick-access"] contentId="#main-content" />

<@liferay_util["include"] page=body_top_include />

<div class="d-flex flex-column min-vh-100">
	<#include "${full_templates_path}/init.ftl">
	<@liferay.control_menu />

	<div class="d-flex flex-column flex-fill position-relative" id="wrapper">
	
	<#include "${full_templates_path}/top-navbar.ftl" />
	<header id="banner" role="banner">
		<nav class="navbar navbar-expand-lg bg-light p-0">
			<div class="container">
				<a class="${logo_css_class}" href="${site_default_url}" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />">
					<img alt="${logo_description}" height="40" src="${site_logo}" width="40" />
				</a>
				
				<#if show_site_name>
					<a class="brand" href="${site_default_url}">
						<span style="font-size: 20px;margin-left: 7px;" title="<@liferay.language_format arguments="${site_name}" key="go-to-x" />" >${site_name} </span>
					</a>
				</#if>
				
				<#include "${full_templates_path}/navigation.ftl" />
			</div>
		</nav>
	</header>

	<section id="content" class="container-fluid">
		<#if selectable>
			<@liferay_util["include"] page=content_include />
		<#else>
			${portletDisplay.recycle()}

			${portletDisplay.setTitle(the_title)}

			<@liferay_theme["wrap-portlet"] page="portlet.ftl">
				<@liferay_util["include"] page=content_include />
			</@>
		</#if>
	</section>
	
	<#include "${full_templates_path}/footer.ftl" />
	
	</div>
</div>

<@liferay_util["include"] page=body_bottom_include />

<@liferay_util["include"] page=bottom_include />

</body>

</html>
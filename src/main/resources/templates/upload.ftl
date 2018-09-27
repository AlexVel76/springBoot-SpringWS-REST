<#import "/spring.ftl" as spring/>

<html>
<body>

	<div>
		<form method="POST" enctype="multipart/form-data" action="/fileUpload">
			<table>
				<tr><td>File to upload:</td><td><input type="file" name="file" /></td></tr>
				<tr><td></td><td><input type="submit" value="Upload" /></td></tr>
			</table>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		</form>
	</div>

	<div>
		<table class="datatable" border="+1">
			<tr>
				<th>File</th>
			</tr>
			<#list files as file>
			<tr>
				<td><a href="${file}">${file}</a></td>
			</tr>
			</#list>
		</table>
	</div>

</body>
</html>

<%@ taglib prefix="s" uri="/struts-tags"%>

<p>Species</p>
<s:if test="species.size > 0">
	<table>
		<s:iterator value="species">
			<tr id="row_<s:property value="id"/>">
				<td>
					<s:property value="scientificName" />
				</td>
				<td>
					<s:property value="commonName" />
				</td>
				<td>
					<s:url id="removeUrl" action="remove">
						<s:param name="id" value="id" />
					</s:url>
					<s:a href="%{removeUrl}" theme="ajax" targets="species">Remove</s:a>
					<s:a id="a_%{id}" theme="ajax" notifyTopics="/edit">Edit</s:a>
				</td>
			</tr>
		</s:iterator>
	</table>
</s:if>


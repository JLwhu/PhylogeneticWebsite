
<s:label name="scientificName" value="${scientificName}" />
<s:textfield name="lastName" label="getText('scientificName')" />

<ol>
<s:iterator value="comboMeals">
  <li><s:property /></li>
</s:iterator>
</ol>
 
<h3>Iterator with IteratorStatus</h3>
<table>
<s:iterator value="#request.comboMeals" status="comboMealsStatus">
  <tr>
  	<s:if test="#comboMealsStatus.even == true">
      <td style="background: #CCCCCC"><s:property/></td>
    </s:if>
    <s:elseif test="#comboMealsStatus.first == true">
      <td><s:property/> (This is first value) </td>
    </s:elseif>
    <s:else>
      <td><s:property/></td>
    </s:else>
  </tr>
</s:iterator>
</table>

	<div class="footer">
		<div>
			<div>
				<h4>24 hour customer service</h4>
				<ul>
					<li class="phone-num">
						352-000-0000 <br> 352-000-0000
					</li>
					<li class="email">
						<a href="#">info@......</a>
					</li>
					<li class="address">
						...... <br> Gainesville, FL 32611
					</li>
				</ul>
			</div>
			<div>
				<h4>Recent Tweets</h4>
				<ul>
					<li>
						<p>
							....
						</p>
						- <span><a href="blog.html">1 day ago</a></span>
					</li>
					<li>
						<p>
							....
						</p>
						- <span><a href="blog.html">2 days ago</a></span>
					</li>
				</ul>
			</div>
			<div class="connect">
				<h4>Get in touch with us</h4>
				<a href="http://www.facebook.com" id="facebook">Facebook</a> <a href="http://www.twitter.com/" id="twitter">Twitter</a> <a href="http://plus.google.com/" id="googleplus">Google+</a>
				<form action="about.html">
					<h4>Newsletter Signup</h4>
					<input type="text" value="Enter email address" onblur="this.value=!this.value?'Enter email address':this.value;" onfocus="this.select()" onclick="this.value='';">
					<input type="submit" id="submit" value="">
				</form>
			</div>
		</div>
		<div>
			<ul>
				<li>
					<a href="<%=request.getContextPath()%>/index.jsp">Home</a>
				</li>
					<li>
						<a href="<%=request.getContextPath()%>/pages/phyloAnalysis/subtreeExtraction.jsp">Phylo Analysis</a>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/pages/phyloAnalysis/subSequenceMapExtraction.jsp">Sequence Extraction</a>
					</li>
			<!-- 	<li>
						<a href="<%=request.getContextPath()%>/pages/googleMap/speciesMap.jsp">Pyhlo Map</a>
					</li> -->	
					<li>
						<a href="<%=request.getContextPath()%>/pages/include/about.jsp">about</a>
					</li>
			</ul>
			<p>
				&#169; 2013 UFL PhyloGenetic Analysis. All Rights Reserved
			</p>
		</div>
	</div>
</body>
</html>
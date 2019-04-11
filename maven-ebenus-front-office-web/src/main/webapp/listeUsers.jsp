<%@ page language="java" 
	         contentType="text/html; charset=UTF-8"
	         pageEncoding="UTF-8"
	         import="com.cours.ebenus.maven.ebenus.front.office.web.FindUsersServlet"
	   %>
<%@page import="java.util.List" %>
<%@page import="com.cours.ebenus.maven.ebenus.dao.entities.Utilisateur" %>

<!DOCTYPE HTML>
<html>

<head>

	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Ebenus</title>
	<link rel="shortcut icon" type="image/x-icon" href="images/logo/favicon.png">
	<!-- CSS files -->
	<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800|Oswald:300,400,500,600,700"
		rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="./assets/css/master.css">
</head>

<body>
	<div class="outer">
		<div class="header-outer" id="header-outer">
			<!-- Header -->
			<header id="header" class="header">
				<div class="header">
					<a href="indexo.html" title="Ebenus Commerce" class="logo">
						<img src="./assets/images/logo/logo.png" alt="Ebenus Commerce">
					</a>
				</div>
			</header>
		</div>
	</div>

	<!-- Section -->
	<section>
		<div class="content">
			<div class="User">
				<h1>liste des utilisateurs</h1>


				<div class="table-responsive">
					<table class="table table-bordered table-hover table-striped">
						<thead>
							<tr>
								<th>prÃ©nom</th>
								<th>nom</th>
								<th>civilite</th>
								<th>identifiant</th>
								<th>Date naissance</th>
								<th>date crÃ©ation</th>
								<th>date modification</th>
								<th>Id rÃ´le</th>
								<th>Identifiant rÃ´le</th>
								<th>description</th>
							</tr>
						</thead>
						<tbody>
							
							<%	List <Utilisateur> userList = (List) request.getAttribute("userList");
							
								for(int i = 0; i < userList.size(); i++){ %>
									<tr>
										<td><%=userList.get(i).getPrenom() %></td>
										<td><%=userList.get(i).getNom() %></td>
										<td><%=userList.get(i).getCivilite() %></td>
										<td><%=userList.get(i).getIdentifiant() %></td>
										<td> <%=userList.get(i).getDateNaissance() %></td>
										<td><%=userList.get(i).getDateCreation() %></td>
										<td><%=userList.get(i).getDateModification() %></td>
										<td><%=userList.get(i).getRole().getIdRole() %></td>
										<td><%=userList.get(i).getRole().getIdentifiant() %></td>
										<td><%=userList.get(i).getRole().getDescription() %></td>
									</tr>
							<%}%>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</section>

	<!-- Footer -->
	<footer>
		<div class="footer-container ">
			<div class="footer">
				<div class="footer-middle">
					<div class="footer-container_">
						<div class="row no-gutters">
							<div class="col-sm-6 col-md-3">
								<div class="block">
									<div class="block-title"><strong><span>Contactez Nous</span></strong></div>
									<div class="block-content">
										<ul class="contact-info">
											<li><i class="icon-location">&nbsp;</i>
												<p><b>Addresse:</b><br>123 Rue la victoire, 75000 Paris, France</p>
											</li>
											<li><i class="icon-phone">&nbsp;</i>
												<p><b>TÃ©l:</b><br>(123) 456-7890</p>
											</li>
											<li><i class="icon-mail">&nbsp;</i>
												<p><b>Email:</b><br><a
														href="mailto:mail@example.com">mail@example.com</a></p>
											</li>
											<li><i class="icon-clock">&nbsp;</i>
												<p><b>Horaire Jours/Heures:</b><br>Lun - Dim / 9:00AM - 8:00PM</p>
											</li>
										</ul>
									</div>
								</div>
							</div>
							<div class="col-sm-6 col-md-3">
								<div class="block">
									<div class="block-title"><strong><span>Mon compte</span></strong></div>
									<div class="block-content">
										<ul class="links">
											<li><i class="icon-right-dir theme-color"></i><a href="#"
													title="About us">Mon compte</a></li>
											<li><i class="icon-right-dir theme-color"></i><a href="#" title="About us">A
													props de nous</a></li>
											<li><i class="icon-right-dir theme-color"></i><a href="#"
													title="Contact us">Contacez nous</a></li>
											<li><i class="icon-right-dir theme-color"></i><a href="pannier.html"
													title="Advanced search">Mon pannier</a></li>
										</ul>
									</div>
								</div>
							</div>

							<div class="col-sm-6 col-md-3">
								<div class="block">
									<div class="block-title"><strong><span>Information</span></strong></div>
									<div class="block-content">
										<ul class="features">
											<li><i class="icon-ok theme-color"></i><a href="#">Historique des
													commandes</a></li>
											<li><i class="icon-ok theme-color"></i><a href="#">Site Map</a></li>
										</ul>
									</div>
								</div>
							</div>

							<div class="col-sm-6 col-md-3">
								<div class="block">
									<div class="block-title"><strong><span>Nos Services</span></strong></div>
									<div class="block-content">
										<ul class="features">
											<li><i class="icon-ok  theme-color"></i><a href="#">Service Client</a></li>
											<li><i class="icon-ok theme-color"></i><a href="#">Les Options de
													Transport</a></li>
											<li><i class="icon-ok  theme-color"></i><a href="#">Avoir et Change</a></li>
											<li><i class="icon-ok  theme-color"></i><a href="#">Politique
													d'Utilisation</a></li>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="footer-bottom">
					<div class="footer-container_">
						<div class="custom-block f-right">
							<div class="block-bottom">
								<div class="custom-block">
									<img src="./assets/images/payment-icon.png" alt=""></div>

							</div>
						</div>
						<address>Â© Ebenus eCommerce. 2018. Tous droit rÃ©servÃ©</address>
					</div>
				</div>
			</div>
		</div>
	</footer>

	</div>
	<!-- JS files -->
	<script src="./assets/js/bower.js" type="text/javascript"></script>
	<script src="./assets/js/application.js" type="text/javascript"></script>
</body>

</html>
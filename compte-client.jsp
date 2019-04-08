<%@ page language="java" 
	         contentType="text/html; charset=windows-1256"
	         pageEncoding="windows-1256"
	         import="com.cours.ebenus.maven.ebenus.front.office.web.LoginServlet"
	   %>
	
	 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	 "http://www.w3.org/TR/html4/loose.dtd">
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
                    <a href="index.html" title="Ebenus Commerce" class="logo">
                        <img src="./assets/images/logo/logo.png" alt="Ebenus Commerce">
                    </a>
                    <ul class="main-menu">
                        <li><a href="index.html">Accueil</a></li>
                        <li><a href="products.html">Produits</a></li>
                        <li><a href="qui-sommes-nous.html">Qui sommes-nous</a></li>
                        <li><a href="contact.html">Contactez-nous</a></li>

                        <li class="last">
                            <a href="#" class="account-link">Mon Compte</a>
                            <ul class="sub-menu">
                                <li><a href="informations-personnelle.html">Mon Compte</a></li>
                                <li class="guest">
                                    <a href="login.html">Login</a>
                                </li>
                                <li class="logged">
                                    <a href="informations-personnelle.html"><strong>Mr John Doe</strong></a>
                                </li>
                                <li><a href="panier.html">Panier</a></li>
                                <li><a href="creer-compte-client.html">Cr�er Compte</a></li>



                            </ul>
                        </li>
                        <li class="header-icon-cart">
                            <a class="menu-cart-icon" href="panier.html"><i class="icon-cart"></i></a>
                        </li>
                    </ul>

                    <div class="phone-block">
                        <i class="icon-phone"></i>
                        Appelez nous<br>
                        <b>+33 5678 890</b>
                    </div>

                    <div class="search-area">
                        <a href="javascript:void(0)" class="search-icon">
                            <!-- <i class="fa fa-search"></i> -->
                            <i class="icon-search"></i>
                        </a>
                        <form id="search_mini_form" action="" method="get">
                            <div class="form-search">
                                <input id="search" placeholder="Rechercher un produit" type="text" name="q"
                                    class="input-text" autocomplete="off">
                                <button type="submit" title="Search" class="button"><i class="icon-search"></i></button>
                            </div>
                        </form>
                    </div>
                </div>
            </header>
        </div>

        <!-- Section -->
        <section>
            <div class="content">
                <div class="customer-acount">
                    <h1>Compte Client</h1>
                    <div class="account-container row no-gutters">
                        <div class="facet-navigation col-md-3">
                            <ul>
                                <li><a href="informations-personnelle.html">Information personnelles</a></li>
                                <li><a href="adresse-client.html">G�rer mon carnet d'adresses</a></li>
                                <li><a href="historique-commandes.html">Historique des commandes</a></li>

                            </ul>
                        </div>
                        <div class=" common-form-controls col-md-9">
                        <% Object value = request.getAttribute("successMessage"); %>
                            <p>Bienvenue Mr <strong><%=value %></strong>,<br />
                                Voici le r�capitulatif de votre compte. Vous pouvez changer vos informations
                                personnelles et g�rer les options de votre compte.</p>
                            <img src="./assets/images/banner.jpg" alt="">
                        </div>
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
                                                    <p><b>T�l:</b><br>(123) 456-7890</p>
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
                                                <li><i class="icon-right-dir theme-color"></i><a href="#"
                                                        title="About us">A props de nous</a></li>
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
                                                <li><i class="icon-ok  theme-color"></i><a href="#">Service Client</a>
                                                </li>
                                                <li><i class="icon-ok theme-color"></i><a href="#">Les Options de
                                                        Transport</a></li>
                                                <li><i class="icon-ok  theme-color"></i><a href="#">Avoir et Change</a>
                                                </li>
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
                            <address>� Ebenus eCommerce. 2018. Tous droit r�serv�</address>
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
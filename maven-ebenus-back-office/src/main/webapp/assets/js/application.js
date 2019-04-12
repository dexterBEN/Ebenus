
/**================================================
JS : MY CUSTOM SCRIPTS
===================================================*/ 

jQuery.extend(jQuery.validator.messages, {
    required: "Ce champs est obligatoire.",
    remote: "Please fix this field.",
    email: "Enter un email valide.",
    url: "Please enter a valid URL.",
    date: "S'il vous plait entrer un format de date valide.",
    dateISO: "Please enter a valid date (ISO).",
    number: "Please enter a valid number.",
    digits: "Please enter only digits.",
    creditcard: "Please enter a valid credit card number.",
    equalTo: "Please enter the same value again.",
    accept: "Please enter a value with a valid extension.",
    maxlength: jQuery.validator.format("Please enter no more than {0} characters."),
    minlength: jQuery.validator.format("Please enter at least {0} characters."),
    rangelength: jQuery.validator.format("Please enter a value between {0} and {1} characters long."),
    range: jQuery.validator.format("Please enter a value between {0} and {1}."),
    max: jQuery.validator.format("Please enter a value less than or equal to {0}."),
    min: jQuery.validator.format("Please enter a value greater than or equal to {0}.")
});

/*=====================================
=            custom select            =
=====================================*/

(function($){
    $(function() {});
})(jQuery);


/*=====  End of custom select  ======*/

/*
  By Osvaldas Valutis, www.osvaldas.info
  Available for use under the MIT License
*/

'use strict';

;( function ( document, window, index )
{
  var inputs = document.querySelectorAll( '.inputfile' );
  Array.prototype.forEach.call( inputs, function( input )
  {
    var label  = input.nextElementSibling,
      labelVal = label.innerHTML;

    input.addEventListener( 'change', function( e )
    {
      var fileName = '';
      if( this.files && this.files.length > 1 )
        fileName = ( this.getAttribute( 'data-multiple-caption' ) || '' ).replace( '{count}', this.files.length );
      else
        fileName = e.target.value.split( '\\' ).pop();

      if( fileName )
        label.querySelector( 'span' ).innerHTML = fileName;
      else
        label.innerHTML = labelVal;
    });

    // Firefox bug fix
    input.addEventListener( 'focus', function(){ input.classList.add( 'has-focus' ); });
    input.addEventListener( 'blur', function(){ input.classList.remove( 'has-focus' ); });
  });
}( document, window, 0 ));
/**================================================
JS : MY CUSTOM SCRIPTS
===================================================*/


(function($){
	$(function(){

		 $(this).scrollTop(0);       
		 
		var path = window.location.href;
		$('.navbar-inverse .navbar-nav > li').find('a').each(function() {
		  if (this.href === path) {
		   $(this).parent().addClass('active');
		  }
		 });

		/*===================================
		=            Date picker            =
		===================================*/

		$('[data-toggle="datepicker"]').datepicker({
      language: 'fr-FR',
      autoHide : true
    });


    /*================================================
    =            adress system management            =
    ================================================*/
    
    jQuery("#shippingAdress").bind('change', function(){        
        val = this.checked; //<---
        if(val){
          $('.adress-box-shipBill').css('display', 'none');
         $('.adress-box-shipping').css('display', 'block');
        }else{
         $('.adress-box-shipping').css('display', 'none');
        }
});

jQuery("#billAdress").bind('change', function(){        
        val = this.checked; //<---
        if(val){
          $('.adress-box-shipBill').css('display', 'none');
         $('.adress-box-billing').css('display', 'block');
        }else{
         $('.adress-box-billing').css('display', 'none');
        }
});

jQuery("#BillShip").bind('change', function(){        
        val = this.checked; //<---
        if(val){
          $('.adress-box-billing').css('display', 'none');
          $('.adress-box-shipping').css('display', 'none');
         $('.adress-box-shipBill').css('display', 'block');
        }else{
         $('.adress-box-shipBill').css('display', 'none');
        }
});


$(".chb1").change(function() {
    var checked = $(this).is(':checked');
    $(".chb1").prop('checked',false);
    if(checked) {
        $(this).prop('checked',true);
    }
});

$(".chb2").change(function() {
    var checked = $(this).is(':checked');
    $(".chb2").prop('checked',false);
    if(checked) {
        $(this).prop('checked',true);
    }
});
    
    /*=====  End of adress system management  ======*/
   
$('.editAdressLink').each(function(index, el) {
  $(this).click(function(event) {
    event.preventDefault();
      $('.EditAdsressForm').toggleClass('hide');
    /* Act on the event */
  });
});

$('.fa-trash,.fa-trash-o').each(function(index, el) {
  $(this).click(function(event) {
    event.preventDefault();
    $(this).closest('tr').remove();
  });
});
		
	})

	
var plugins = {
  contactForm: $('._validate')
  };


if (plugins.contactForm.length) {

    var $contactform = plugins.contactForm;



// add the rule here
 $.validator.addMethod("valueNotEquals", function(value, element, arg){
  return arg !== value;
 }, "Value must not equal arg.");

 $.validator.addMethod("anyDate",
    function(value, element) {
        return value.match(/^(0?[1-9]|[12][0-9]|3[0-1])[/., -](0?[1-9]|1[0-2])[/., -](19|20)?\d{2}$/);
    },
    "S'il vous plait entrer un format de date valide."
);


    var validator =  $contactform.validate({
      rules: {
         select_city: { 
          valueNotEquals: "default" 
        },
        firstname: {
          required: true,
          minlength: 2
        },
        lastname: {
          required: true,
          minlength: 2
        },
        password: {
          required: true,
          minlength: 2
        },
        email: {
          required: true,
          email: true
        },
        dteNaiss: {
          required: true,
          anyDate: true
        },
        street: {
          required: true,
          minlength: 2
        },
        postal_code: {
          required: true,
          number: true
        },
        country: {
          required: true,
          minlength: 2
        }

      },
      messages: {
        select_adress_: { 
          valueNotEquals: "Selectionner un element s'il vous plait !" 
        },
        firstname: {
          required: "Remplir votre Prenom s'il vous plait.",
          minlength: "Vous devez avoir au moins 2 caracteres pour votre Prenom."
        },
        lastname: {
          required: "Remplir votre Nom s'il vous plait.",
          minlength: "Vous devez avoir au moins 2 caractere pour votre Nom."
        },
        password: {
          required: "Remplir votre mot de passe s'il vous plait.",
          minlength: "Vous devez avoir au moins 2 caractere pour votre mot de passe."
        },
        email: {
          required: "S'il vous plait remplir votre Identifiant."
        },
        dteNaiss: {
          required: "S'il vous plait remplir votre Date de naissance."
        },
        street: {
          required: "S'il vous plait remplir votre Rue.",
          minlength: "Vous devez avoir au moins 2 caracteres pour votre Rue."
        },
        postal_code: {
          required: "S'il vous plait remplir votre Code Postal.",
          number: "S'il vous plait remplir votre Code Postal avec des chiffres."
        },
        country: {
          required: "S'il vous plait remplir votre Pays.",
          number: "Vous devez avoir au moins 2 caracteres pour votre Payes."
        }
      }
      })


   validator.resetForm();
      }


	 
})(jQuery);


// Toggle the table of contents
$( "button#tocbutton" ).click(function() {
  if ($("#tocbutton").css('left') == '25px') {
    $( "#tocbutton" ).animate({left: '270px'},"slow");
    $( "#toc.toc2" ).animate({left: '0'},"slow");
    $( "#menubar" ).animate({left: '215px'},"slow");
    $( "#header, #content, #footnotes, #footer" ).animate({left: '260px'},"slow");
    $( "body.toc2").css("max-width", "80%");
    $( "#menubar" ).css("max-width", "80%");
}
  else {
    $( "#menubar" ).animate({left: '10px'},"slow");
    $( "#tocbutton" ).animate({left: '25px'},"slow");
    $( "#toc.toc2" ).animate({left: '-260px'},"slow");
    $( "#header, #content, #footnotes, #footer" ).animate({left: '40px'},"slow");
    $( "body.toc2").css("max-width", "90%");
}
});
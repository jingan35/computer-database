$(document).ready(function() {
    $("#locales").change(function () {
        var selectedOption = $('#locales').val();
        var CheminComplet = document.location.href;
        var indexOfAskMark = CheminComplet.indexOf('?');
        var NomDuFichier     = indexOfAskMark==-1?CheminComplet.substring(CheminComplet.lastIndexOf( "/" )+1):
        	CheminComplet.substring(CheminComplet.lastIndexOf( "/" )+1 , indexOfAskMark);
        var indexOfLangAnd = CheminComplet.indexOf('&lang');
        var indexOfLangAsk = CheminComplet.indexOf('?lang');
        if (selectedOption != ''){
        	indexOfAskMark==-1?window.location.replace(NomDuFichier+'?lang=' + selectedOption):window.location.replace((
        			(indexOfLangAnd==-1&&indexOfLangAsk==-1)?
        					CheminComplet+'&lang=' + selectedOption:
        						CheminComplet.substring(0,indexOfLangAsk!=-1?indexOfLangAsk:indexOfLangAnd)+(indexOfLangAsk!=-1?'?lang=':'&lang=') + selectedOption) );
        }
    });
});
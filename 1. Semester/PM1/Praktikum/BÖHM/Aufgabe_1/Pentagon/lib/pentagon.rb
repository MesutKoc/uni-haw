# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.


# Purpose:    Funktion schreiben, welches die Oberfläche eines Pentagondodekaeder berechnet.
#             Argumente + Resultat sollen Float sein.
# Contrackt:  Penta::=(a)
#             :: Float -> Float :::: (3 * a**2 ) * FORMEL
# Test: {
#           [0.0] => Err, [1.0] => 20.64573, [2.0] => 82.58292, [3.0] => 185.81156, ['a'] => Err,
#           []=> Err 
#       }

$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','ext_pr1/lib')
require 'ext_pr1_v4'

Formel= Math.sqrt(5*(5+2*Math.sqrt(5))) #Global

def penta(a)
check_pre((a.float? and a >=1.0 ))
(3*(a**2))* Formel
end
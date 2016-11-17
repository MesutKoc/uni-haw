require 'person'
require 'brief'
# 
# Author:: Sascha Majewsky
# Author:: Maximilian Janzen
# Ein simples Programm, welches neue Personen erschafft.


#RUnit
p1 = Person.new("Otto","Langestrasse","22119","Hamburg")   #person 1 existiert
p2 = Person.new("Gustav","Dunklestrasse","22111","Hamburg")  #person 2 existiert
p3 = Person.new("Thomas","Hintergasse","22001","Hamburg")  #person 3 existiert
p4 = Person.new("Oliver","Berliner Tor 12", "22206", "Hamburg") #person 4 existiert

puts p1.schreiben("Frau Schüler, Ihre Aufgabe ist nicht Korrekt abgegeben!", sandra)
puts p2.schreiben("Frau Schüler, ich wollte Sie nur daran erinner endlich mal was richtiges abzugeben!", sandra)
puts p3.schreiben("HASSSS!!! Ich hab so ein Hass!", hatem)
puts p4.schreiben("San, chill. Was denn los?", sandra)
puts p3.schreiben("Willst du mich veräppeln? Die haben meine Aufgaben wieder nicht abgenommen.", hatem)
puts p2.schreiben("OK", sandra)

# 
# Author:: Sascha Majewsky
# Author:: Maximilian Janzen
# Ein simples Programm, welches eine Person darstellt.


class Person
  attr_accessor :name, :strasse, :plz, :ort #Verändert reader zu writer.
  #Person kann Namen und Adresse ändern
  def initialize(name, strasse, postleitzahl, ort)
    @name = name
    @strasse = strasse
    @plz = postleitzahl
    @ort = ort
    @briefe = [] #Kombiniert gesendet und empfangen
  end

  def schreiben(inhalt, empfaenger)
    brief = Brief.new(empfaenger, self, inhalt)
    schicken = false
    if @briefe.include?(brief) == false 
      @briefe.push(brief)
      empfaenger.erhalten(brief)
      schicken = true
    end
    schicken
  end

  def erhalten(brief)
    @briefe.push(brief) #Bereits auf Gleichheit überprüft für die 'schreiben' Methode
  end
  protected :erhalten  #Damit bei einem doppelten Brief 
end
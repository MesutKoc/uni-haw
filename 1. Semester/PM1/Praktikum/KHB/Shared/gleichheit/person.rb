require 'set'

#
#
#
#

class Person
 attr_reader :name, :strasse, :postleitzahl, :ort, :briefe_geschickt, :briefe_erhalten
  
  def initialize (name, strasse, postleitzahl, ort)
    @name = name
    @strasse = strasse
    @postleitzahl = postleitzahl
    @ort = ort
    @briefe_geschickt = Set.new #set in dem die objekte gespeichert werden sollen
    @briefe_erhalten = Set.new #set in dem die objekte gespeichert werden sollen
  end
 
  def == (other)
    # if nil dann kann es nicht das gleiche Objekt sein
    #wenn gleich dann true 
    #etc
  end
  
  def to_s
    "#{@Name}, #{@strasse}, #{@postleitzahl}, #{ort}"
  end
  
  
end
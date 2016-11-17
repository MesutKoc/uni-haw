require 'rexml/document'

class XMLlesen
  include REXML, Comparable
  
  attr_reader :xmlfile, :xmldoc
  
  def initialize(xmlf)
    return false if !xmlf.is_a?(String)
    @xmlfile = File.new(xmlf)
    @xmldoc = Document.new(xmlfile)
  end
  
  def gebe_alle_movies_aus
    xmldoc.elements.each("cia/country"){ 
       |e| puts "Name: #{e.attributes["name"]} hat die Größte Population mit [#{population_am_groessten}]"
    }
  end
  
  def population_am_groessten
    population = []
    xmldoc.elements.each("cia/country"){ 
           |e| population << e.attributes["population"] if !e.attributes["population"].is_a?(NilClass) 
    }
    population.max
  end
  
  def to_s
    "[#{self.class}][#{xmldoc}]"
  end
end


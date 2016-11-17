class Adresse
  attr_reader :strasse, :hnr, :plz, :ort
  
  # Konstruktor initialisiert das Objet
  def initialize(str, hnr, plz, ort)
    @strasse = str
    @hnr = hnr
    @plz = plz
    @ort = ort
  end
  
  def to_s
    "Adresse(#@strasse,#@hnr, #@plz, #@ort)"
  end
  
  def to_a
    [self.plz, self.ort, self.strasse, self.hnr]
  end
  
  def ==(other)
    self.equal?(other) or (other.is_a?(self.class) and (self.to_a == other.to_a))
  end
  
  def <=>(other)
    return false if other.nil? || !other.is_a?(self.class)
    return true if self.equal?(other)
    return self.to_a <=> other.to_a
  end
  
  def eql?(aHash)  
    self.equal?(aHash) or (aHash.hash? and self.size == aHash.size and self.to_a.sort == aHash.to_a.sort)
  end
  
  def hash
   return to_s.hash
  end
  
  def sortiere_absteigend_nach_strasse_hnr(adresse_ary)
    adresse_ary.sort {|a,b| [b.strasse, b.hr] <=> [a.strasse, a.hr]}
  end
end


puts test
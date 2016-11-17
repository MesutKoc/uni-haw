class Zelle
  # Eine Zelle erhaelt bei der Initialisierung Zeile i und Spalte j der Zelle (Typ Integer),
  # einen offset (Typ Point), der die Verschiebung des gesamten Gitters in x, y Richtung beinhaltet,
  # und eine Groesse groesse (Typ Integer) fuer die Breite und Hoehe.
  # Aus Zeile, Spalte, offset und groesse wird die Position der Zelle @pos (die obere linke Ecke) bestimmt
  #
  # Die Darstellung der Zelle erfolgt als Rechteck.
  # Aus @pos und der groesse werden die Koordinaten des Rechtecks bestimmt:
  # @pos.x,@pos.y für die obere linke Ecke und pos.x+groesse, pos.y+greosse für die untere rechte Ecke.
  # Aus Gründen der Performance wird hier direkt das Grafikobjekt (TkcRectangle) erzeugt.
  #
  # Der Parameter lebendig steuert, ob das Rechteck gefüllt oder nicht gefüllt dargestellt wird.
  def initialize(offset,i,j,groesse,lebendig=false,farbe=:black)

    @zelle = [i,j]
    @pos = Point.new(j*groesse+offset.x,i*groesse+offset.y)
    @lebendig = lebendig
    @farbe = farbe
    fill = lebendig?() ? @farbe : :white
    @darstellung = TkcRectangle.new(Leinwand.gib_einzige_instanz(),@pos.x,@pos.y,@pos.x+groesse,@pos.y+groesse,:fill=>fill,:activefill=> fill,:outline=>@farbe)
  end

  # Zellen sterben bedeutet, dass sich der Zustand lebendig auf false verändert
  def sterben()
    @lebendig = false
  end

  # Zellen leben bedeutet, dass sich der Zustand lebendig auf true verändert
  def leben()
    @lebendig = true
  end

  def lebendig?()
    return @lebendig
  end

  # Darstellung als Zeichenkette
  def to_s()
    "Z([#{@zelle[0]},#{@zelle[1]}]#{@pos.x},#{@pos.y},#@lebendig)"
  end

  # Methoden fuer die Darstellung
  # Zeichnet eine Zelle auf der Leinwand
  # und benutzt als Füllfarbe @farbe, wenn die Zelle lebt, sonst :white
  def sichtbar_machen()
    fill = lebendig?() ? @farbe : :white
    # Ändert die Reihenfolge im Canvas Stack
    Leinwand.gib_einzige_instanz().raise(@darstellung)
    @darstellung[:fill] = fill
    @darstellung[:outline] =@farbe
  end

  # Löscht eine Zelle auf der Leinwand
  def unsichtbar_machen()
    @darstellung[:fill] = :white
    @darstellung[:outline] = :white
  end

end
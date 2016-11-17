class Schachbrett
  def initialize()
    @spielbrett = Array.new(8){Array.new(8)}
  end

#  def [](index)
#    return @spielbrett[index]
#  end
#
#  def []=(index,wert)
#    @spielbrett[index] = wert
#  end  
  
  def zug(von_s,von_z,nach_s,nach_z)
    figur = @spielbrett[von_z][von_s]
    if (nach_z < 8 && nach_s < 8 &&
      figur && 
      figur.ziehe(von_s,von_z,nach_s,nach_z))
      @spielbrett[nach_z][nach_s] = figur
      @spielbrett[von_z][von_s] = nil
    else
      puts "ungültig"
    end
  end

  def to_s
    return @spielbrett.to_s
  end
end

class Schachfigur
  def initialize(farbe)
    @farbe = farbe
  end

  def ziehe(von_s,von_z,nach_s,nach_z)
    # ???
  end
end

class Bauer < Schachfigur
  def initialize(farbe,richtung)
    super(farbe)
    @bewegt = false
    @richtung = richtung
  end

  def ziehe(von_s,von_z,nach_s,nach_z)
    if (von_s == nach_s)
      if !@bewegt
        if (von_z-nach_z == 2*@richtung )
          return true
        elsif
        (von_z-nach_z == @richtung )
          return true
        else
          return false
        end
        if (von_z-nach_z == @richtung )
          return true
        else
          return false
        end
      else
        return false
      end
    end
  end
end


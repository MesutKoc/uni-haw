require "Zelle"
require "Point"

class Life
  # TODO
  def initialize(n,muster_index)
    @muster_index = muster_index
    @muster = [[1,2],[2,3],[3,1],[3,2],[3,3]], # 0: Gleiter, Vorgabe Wendholt, gleitet uebers Spielfeld
    [[2,2],[2,3],[2,4]],# 1: Oszilator, blinkt
    [[1,1],[1,4],[2,5],[3,1],[3,5],[4,2],[4,3],[4,4],[4,5]],# 2: Spaceship, fliegt von links nach rechts
    [[1,2],[1,3],[1,4],[2,3],[3,2],[3,3],[3,4]],# 3: Boat - teilt sich und stirbt
    [[1,3],[1,4],[2,2],[2,4],[3,3]]# 4: Stilleben, macht nichts, gaehn
    @n = n
    @gitter = {}
    i = 0
    p = Point.new(25,25)
    @zaehler = []

    while i < @n do    #i ist die zeile
      j = 0
      while j < @n do
        a = [i,j]  #geht alle Tupel durch
        if @muster[@muster_index].include?(a)
          @gitter[a] = Zelle.new(p,i,j,30,true) #wenn das tupel im muster ist setzte die zelle lebendig
        else
          @gitter[a] = Zelle.new(p,i,j,30,false)
        end
        j += 1
      end
      i += 1
    end

  end

  # TODO
  def naechste_generation()

    speicher_tot = []
    speicher_neu = []

    @gitter.each { |key, value|
      anzahl = lebende_nachbar_zellen(key[0], key[1])
      if !value.lebendig? && anzahl == 3
        speicher_neu.push(key)
      end
      if value.lebendig? && anzahl < 2
        speicher_tot.push(key)
      end
      if value.lebendig? && (anzahl == 2 || anzahl == 3)
        speicher_neu.push(key)
      end
      if value.lebendig? && anzahl  > 3
        speicher_tot.push(key)
      end
    }

    speicher_tot.each { |toete| @gitter[toete].sterben()
    }
    speicher_neu.each { |toete| @gitter[toete].leben()
    }

    self.sichtbar_machen()
  end

  #TODO
  def lebende_nachbar_zellen(i,j)

    n = @n
    counter = 0
    betrachte = [[(i-1)%n, (j-1)%n] , [(i-1)%n,j], [(i-1)%n,(j+1)%n],
      [i,(j-1)%n], [i,(j+1)%n], [(i+1)%n,(j-1)%n], [(i+1)%n,j], [(i+1)%n,(j+1)%n]]

    betrachte.each { |nachbar|
      if @gitter[nachbar].lebendig?
        counter = counter +1
      end
    }
    counter
  end

  #TODO
  def unsichtbar_machen()
    @gitter.each_value { |zelle| zelle.unsichtbar_machen}
  end

  #TODO
  def sichtbar_machen()
    @gitter.each_value { |zelle| zelle.sichtbar_machen}
  end

  #TODO
  def zuruecksetzen()
    self.unsichtbar_machen()
    initialize(@n,@muster_index)
  end

  #
  # Vorgegebene Methode der Klasse Life
  #

  # Simuliert die Entwicklung der Generationen des Game Of Life für n - Wiederholungen
  #
  # Dazu wird alle 10 ms die Folgegeneration mit Hilfe der Methode
  # naechste_generation berechnet und dargestellt
  #
  def simuliere(schritte)
    TkTimer.new(10,schritte, proc{
      naechste_generation()
    }).start(0, proc{})
  end

end
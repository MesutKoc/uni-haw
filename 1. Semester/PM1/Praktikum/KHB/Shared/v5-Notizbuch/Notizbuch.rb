require 'Einstellungen'

class Notizbuch
  def initialize()
    @notizen = Array.new() # @notizen = []
    @number = instanzen_zaehler()
  end

  def notiz_speichern(notiz)
    @notizen << notiz
  end

  def anzahl_notizen()
    return @notizen.length()
  end

  def notiz_zeigen(nummer)
    if (0<= nummer && nummer < @notizen.length() )
      puts @notizen[nummer]
    end
  end

  def notiz_entfernen(nummer)
    if (0...anzahl_notizen).include?(nummer)
      @notizen.delete_at(nummer)
    end
  end

  def notizen_ausgeben()
    @notizen.each {|notiz|
      puts(notiz)
    }
  end

  def notizen_ausgeben()
    for notiz in @notizen
      puts(notiz)
    end
  end

  def notizen_nummerieren()
    index = 0
    while(index < anzahl_notizen())
      puts("#{index}: #{@notizen[index]}")
      index += 1
    end
  end

  def notizen_nummerieren()
    @notizen.each_index(){ |index|
      puts("#{index}: #{@notizen[index]}")
    }
  end

  def notiz_austauschen(nummer,neue_notiz)
    if (0...anzahl_notizen).include?(nummer)
      @notizen[nummer] = neue_notiz
    end
  end

  def wort_finden_in_notiz(wort)
    @notizen.each{ |notiz|
      if notiz.include?(wort)
        return notiz
      end
    }
  end

  def alle_notizen_mit_wort(wort)
    alle_notz = []
    @notizen.each{ |notiz|
      if notiz.include?(wort)
        alle_notz << notiz
      end
    }
    return alle_notz
  end

  def tauschen(wort,neues_wort)
    @notizen.each_index{ |index|
      if @notizen[index] == wort
        @notizen[index] = neues_wort
      end
    }
  end

  def alle_notizen_mit_wort_loeschen(wort)
    index = 0
    while index < @notizen.length
      if @notizen[index].include?(wort)
        @notizen.delete_at(index)
      else
        index += 1
      end

    end
    return self
  end
end
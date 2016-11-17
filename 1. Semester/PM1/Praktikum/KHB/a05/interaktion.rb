require_relative 'nummergenerator'
require_relative 'mastermindlogik'
require_relative 'mastermindalgorithmus'

#
# Author:: Sascha Majewsky
# Author:: Maximilian Janzen
#

class Interaktion
  def initialize
    @leben = 10
    @zu_erratende_nummer = []
    @tipp = []
    @mensch_hat_geraten = 0
  end

  def spiel_starten
    puts "Herzlich Willkommen bei Mastermind! Sie haben 10 Versuche um die 4-stellige Zahlenkombination zu erraten. \nBei jedem Versuch können Sie einen Tipp abgeben, wie die Zahlenkombination wohl aussieht. \nBefindet sich eine Ihrer Zahlen Ihres Tipps genau an der richtigen Stelle der Zahlenkombination, dann erhalten Sie einen schwarzen Marker. \nWenn eine von Ihnen getippte Zahl nur innerhalb der Zahlenkombination vorhanden ist erhalten Sie einen weißen Marker \nNach 10 Versuchen ist Schluss! Versuchen Sie es so schnell wie möglich die Zahlenkombination zu erraten. Und nun los! \n\n"
    @zu_erratende_nummer = NummerGenerator.new.generiere_nummer
    spieler_oder_mensch ? raten_mensch : raten_computer
  end

  def spieler_oder_mensch
    puts "\nMöchten Sie selber Ihr Glück versuchen dann tippen Sie \"ich\" ansonsten wird Ihr Computer es für Sie lösen."
    spieler_frage = gets.chomp
    spieler_frage == "ich" ? true : false
  end

  def raten_computer
    @mensch_hat_geraten = 0
    ziel = MastermindAlgorithmus.new.knuth_algo(@zu_erratende_nummer)
    @tipp = ziel[0].flatten
    @leben = ziel[1]
    raten_analyse
  end

  def raten_mensch
    @tipp = []
    @mensch_hat_geraten = 1
    puts "Bitte geben Sie ihre 4-stellige Zahl an, von der Sie ausgehen, dass es die gesuchte Zahlenkombination ist. Dies kostet Sie eines Ihrer #{@leben} Leben."
    puts "Die zu erratende Zahl lautet dieses Mal: #{@zu_erratende_nummer.to_s}."   # test

    while @tipp.length < 4
      eingabe = gets.chomp.to_i
      @tipp << eingabe if eingabezahl_analyse(eingabe) >= 0
    end
    @leben -= 1
    raten_analyse
  end

  def eingabezahl_analyse(eingabe)
    if (!eingabe.is_a?(Integer) || eingabe < 0 || eingabe > 9) || (@tipp.include?(eingabe))
      puts "Bitte geben Sie nur einzigartige, ganze Zahlen von 0 bis 9 ein."
      return -1 # Nur zum abbrechen
    end
    eingabe
  end

  def raten_analyse
    puts "Der Tipp lautet: #{@tipp.to_s}"
    logik1 = MastermindLogik.new
    marker = MastermindLogik.new().beide_marker_setzen(@tipp,@zu_erratende_nummer)
    puts "\nBei diesem Tipp erhalten Sie #{marker[0]} weisse Marker und #{marker[1]} schwarze Marker.\n\n"

    return gewonnen if @tipp == @zu_erratende_nummer
    return verloren  if @leben == 0
    raten_mensch if (@leben > 0) && (@mensch_hat_geraten == 1)  #falls er nicht richtig rät mensch_hat_geraten
  end

  def verloren
    puts "Leider haben Sie die Kombination nicht erraten und verloren! Die Kombination lautete: #{@zu_erratende_nummer}."
    erneut_spielen ? spiel_starten : exit
  end

  def gewonnen
    puts "Juhu, Sie haben gewonnen und haben dafür sogar nur #{10-@leben} Versuch(e) gebraucht."
    erneut_spielen ? spiel_starten : exit
  end

  def erneut_spielen
    puts "Möchten Sie erneut spielen? Dann geben Sie \"ja\" ein."
    @leben = 10
    nochmal_spielen = gets.chomp
    nochmal_spielen == "ja" ? true : false
  end
end
# 
# Author:: Sascha Majewsky
# Author:: Maximilian Janzen
# Ein simples Programm, welches einen Brief darstellt.

class Brief
  attr_reader :absender, :empfaenger, :inhalt
  def initialize (absender, empfaenger, inhalt)
    @absender = absender
    @empfaenger = empfaenger
    @inhalt = inhalt
  end
  
  def ==(other)
    return false if other.nil?
    return true if self.equal?(other)
    return [@absender, @empfaenger, @inhalt] == [other.absender, other.empfaenger, other.inhalt]    
  end
end


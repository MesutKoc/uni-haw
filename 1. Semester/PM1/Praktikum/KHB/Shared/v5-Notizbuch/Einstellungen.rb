
# generischer Instanzenzaehler für alle Klassen
# Dies zu verstehen wird Ihnen erst am Ende der Vorlesung möglich sein
class Object
  class << self
      attr_reader :instances
      attr_writer :instances
  end
  def instanzen_zaehler()
    self.class.instances ||=0
    self.class.instances +=1
  end
  def intern_to_s()
    string = self.class.to_s
    string[0] = string[0,1].downcase
    return "#{string}_#{number}"
  end
  protected
  attr_writer :number     
  attr_reader :number
end



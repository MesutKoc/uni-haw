require 'tk'
require 'Einstellungen'

class Leinwand < TkCanvas

  private_class_method :new
  @@einzige_instanz = nil
 
  def Leinwand.gib_einzige_instanz(parent=nil,*args,&block)
    if @@einzige_instanz == nil 
      @@einzige_instanz = new(parent,*args,&block)
    end
    return @@einzige_instanz
  end
    
end
class POI
  include Comparable
  attr_reader :geo_coord, :attachments, :name
  protected :attachments
  
  def initialize(name, geo_coord)
    @name = name
    @geo_coord = geo_coord
    @attachments = []
  end

  def <<(attachment)
    return self if !attachment.is_a(Attachment)
    @attachments << attachment if !(@attachments.include?(attachment))
    return self
  end

  def to_s()
    return "POI(#@name,#@geo_coord,\{#{@attachments.sort().join(",")}\}"
  end

  def ==(obj)
    self.equal?(obj) or (obj.is_a?(self.class) and (self.to_a == obj.to_a))
  end
  
  def to_a
    [self.name, self.geo_coord, self.attachments.sort]
  end

  def hash
    to_s.hash
  end

  def eql?(aHash)
    self.equal?(aHash) or (aHash.hash? and self.size == aHash.size and self.to_a.sort == aHash.to_a.sort)
  end

  def <=>(obj)
    return false if obj.nil? || !obj.is_a?(self.class)
    return true if obj.equal?(self)
    return self.to_a <=> obj.to_a
  end
end

class Geokoordinate
  include Comparable
  attr_reader :bg, :lg
  
  def initialize(breitengrad,laengengrad)
    @bg = breitengrad
    @lg = laengengrad
  end

  def to_s()
    return "(#@bg,#@lg)"
  end
  
  def to_a
    [self.bg, self.lg]
  end

  def ==(other)
    self.equal?(other) or (other.is_a?(self.class) and (self.to_a == obj.to_a))
  end

  def hash
    to_s.hash
  end
  
  def eql?(aHash)
    self.equal?(aHash) or (aHash.hash? and self.size == aHash.size and self.to_a.sort == aHash.to_a.sort)
  end

  def <=>(other)
    return false if other.nil? || !other.is_a?(self.class)
    return true if other.equal?(self)
    return self.to_a <=> other.to_a
  end
end

class Attachment
  include Comparable
  attr_reader :name, :inhalt
  def initialize(name,inhalt)
    @name = name
    @inhalt = inhalt
  end

  def to_s()
    "At[#@name,#@inhalt]"
  end
  
  def to_a
    [self.name, self.inhalt]
  end

  def ==(other)
    self.equal?(other) or (other.is_a?(self.class) and (self.to_a == other.to_a))
  end

  def hash
    return to_s.hash
  end

  def eql?(aHash)
    self.equal?(aHash) or (aHash.hash? and self.size == aHash.size and self.to_a.sort == aHash.to_a.sort)
  end

  def <=>(other)
    return false if other.nil? || !other.is_a?(self.class)
    return true if self.equal?(other)
    return self.to_a == other.to_a
  end
end
module Sequences
  def Sequences.harmonic_recursive(n)
    if n < 1
      -1
    elsif n==1
      1
    else
      harmonic_recursive(n-1) + 1.0/n
    end
  end

  def Sequences.harmonic_accumulative(n, result = 0)
    return -1 if n < 0
    return result  if n==0
    return harmonic_accumulative(n-1, result + 1.0/n)

  end

  def Sequences.fib_rec(n)

  end

  def Sequences.fib_iter(n)
    if n==0||n==1
      return n
    end
    f1 = 0
    f2 = 1
    (2..n).each do
      old_f1 = f1
      f1 = f1 + f2
      f2 = old_f1
    end
  end

  def Sequences.collatz(n, start = 1)

  end
end

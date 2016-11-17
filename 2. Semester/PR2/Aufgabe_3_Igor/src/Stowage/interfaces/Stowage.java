package Stowage.interfaces;

import Utils.interfaces.Mutable;
import Transport.interfaces.Body;

public interface Stowage<E> extends Mutable, Body, WithCargo, Bounded3DimStack<E> {
    // ...
}
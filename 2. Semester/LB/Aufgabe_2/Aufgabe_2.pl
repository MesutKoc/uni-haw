%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%                                                      %
%   Author: Mesut & Anton                              %
%   Prof: Herr Klauck (HAW)                            %
%   Aufgabe: 2                                         %
%                                                      %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%**************************************************************
% Aufgabe 2.1)                                                *
% print_komp zeigt alle Komponisten aus der Datenbank         *
% =========================================================== *
% SCHEMA: komponist(KNR, VORNAME, Name, GEBOREN, GESTORBEN)   *
%**************************************************************
print_komp :- komponist(KNR, _VORNAME, Name, _GEBOREN, _GESTORBEN),     % Wir wollen alle Datensätze mit dem KNR und Name
              integer(KNR),                                             % Hier prüfen wir, ob ein KNR eventuell "null" ist. Denn diese wollen wir ja gar nicht haben!
              write(KNR), write('  '), write(Name), writeln('.'),fail.  % write alle KNR mit den Namen von allen Komponisten. Mit fail setzen wir gezielt das Backtracking ein.
print_komp.                                                             % Damit die Abfrage am Ende uns kein ,,false" anzeigt, sagen wir, dass die Funktion beim Aufruf ein true ist.

%*****************************************************************************
% Aufgabe 2.1.2)                                                             *
% print_az, das alle Komponisten, die im 18. Jahrhundert (von 1700 bis 1799) *
% geboren wurden, auf dem Bildschirm ausgibt.                                *
% ========================================================================== *
% DB-SCHEMA: komponist(KNR, VORNAME, Name, GEBOREN, GESTORBEN)               *
%*****************************************************************************
print_az :- komponist(KNR, _VORNAME, Name, GEBOREN, GESTORBEN),
            integer(KNR),                                                 % Hier prüfen wir, ob ein KNR eventuell "null" ist. Denn diese wollen wir ja gar nicht haben!
            GEBOREN >= (1700),                                            % Ist der Komponist im Jahre 1700 oder höher geboren?
            GESTORBEN =< (1799),                                          % Ist der Komponist früher oder genau 1799 gestorben?
            write(Name),write('  '),                                      % Gib die Namen der Komponisten aus, bei denen dieses wahr ist.
            write(' (GEBOREN: '), write(GEBOREN),                         % Gebe 'Geboren' aus
            write(', GESTORBEN: '), write(GESTORBEN), writeln(')'), fail. % Gebe 'Gestorben' aus
print_az.                                                                 % Damit die Abfrage am Ende uns kein ,,false" anzeigt, sagen wir, dass die Funktion beim Aufruf ein true ist.

%**************************************************************************************
% Aufgabe 2.1.3)                                                                      *
% print_dp(HListe), das alle Titel der CDs auf dem Bildschirm ausgibt,                *
% die von bestimmten Firmen produziert wurden, etwa  von 'Decca' oder von 'Philips'.  *
% =================================================================================== *
% DB-SCHEMA: cd(CDNR, NAME, HERSTELLER, ANZ_CDS, GESAMTSPIELZEIT).                    *
%**************************************************************************************
print_db1(HListe) :- cd(_CDNR, NAME, HListe, _ANZ_CDS, _GESAMTSPIELZEIT),    % Wir setzen direkt HListe bei der Position von Hersteller ein.
                     writeln(NAME), fail.                                    % Gebe den Namen aus
print_db1(_).                                                                % Damit die Abfrage am Ende uns kein ,,false" anzeigt, sagen wir, dass die Funktion beim Aufruf ein true ist.

%************************************************************************
% Aufgabe 2.1.4)                                                        *
% print_soli(Titel), das alle Solisten auf dem Bildschirm ausgibt,      *
% die in einem bestimmten Stück (Titel) in allen Aufnahmen auftreten.   *
% ===================================================================== *
% SCHEMA: stueck(SNR, KNR!komponist, TITEL, TONART, OPUS).              *
%         solist(CDNR!aufnahme, SNR!aufnahme, NAME, INSTRUMENT).        *
%************************************************************************
print_soli(Titel) :-  stueck(SNR, _KNR, Titel, _TONART, _OPUS),   % Wir setzen den Parameter Titel direkt bei Stück ein und,
                      integer(SNR),                               % Hier prüfen wir, ob ein SNR eventuell "null" ist. Denn diese wollen wir ja gar nicht haben!
                      aufnahme(CDNR, SNR, _ORCHESTER, _LEITUNG),  % Wir verleichen die PK's von Aufnahme und Solisten,
                      integer(CDNR),                              % Hier prüfen wir, ob ein CDNR eventuell "null" ist. Denn diese wollen wir ja gar nicht haben!
                      solist(CDNR, SNR, NAME, _INSTRUMENT),       % vergleichen den Primärschlüssel von SNR und solist!SNR
                      writeln(NAME), fail.                        % Gebe den Namen (Solist) aus
print_soli(_).                                                    % Damit die Abfrage am Ende uns kein ,,false" anzeigt, sagen wir, dass die Funktion beim Aufruf ein true ist.

%***********************************************************************
% Aufgabe 2.1.5)                                                       *
% print_komp(Name), das alle Komponisten auf dem Bildschirm ausgibt,   *
% bei deren Werke ein bestimmter Solist (Name) mitgewirkt hat.         *
% ==================================================================== *
% DB-SCHEMA: komponist(KNR, VORNAME, Name, GEBOREN, GESTORBEN).        *
%         solist(CDNR!aufnahme, SNR!aufnahme, NAME, INSTRUMENT).       *
%***********************************************************************
print_komp(Name) :- solist(_CDNR, SNR, Name, _INSTRUMENT),                % Hier wird der Parameter direkt eingesetzt unter Name zusehen. Die SNR nummer wird direkt weitergereicht.
                    integer(SNR),                                         % Hier prüfen wir, ob ein SNR eventuell "null" ist. Denn diese wollen wir ja gar nicht haben!
                    komponist(KNR, _VORNAME, NAME, _GEBOREN, _GESTORBEN), % KNR belegen wir hiermit (s. untere Zeile)
                    integer(KNR),                                         % Hier prüfen wir, ob ein KNR eventuell "null" ist. Denn diese wollen wir ja gar nicht haben!
                    stueck(SNR, KNR, _TITEL, _TONART, _OPUS),             % Überprüfe, ob die Primärschlüssel identisch sind (KNR).
                    write(NAME),write(' '),fail.                          % Gebe den Namen der Komponisten aus.
print_komp(_).                                                            % Damit die Abfrage am Ende uns kein ,,false" anzeigt, sagen wir, dass die Funktion beim Aufruf ein true ist.

%**************************************
% Aufgabe 2.2.1 zugleich 2.2.2        *
% mnot/1, and/2, or/2, nand/2, nor/2, *
% xor/2, impl/2, xor/2 und aequiv/2   *
% ==================================  *
% Legende -| = Negation               *
%**************************************
mnot(X)    :- X, !, fail.                        % not(X)
mnot(_X).
and(X,Y)   :- X,Y.                               % A ^ B
or(X,Y)    :- (X,!); Y.                          % A v B
nand(X,Y)  :- mnot(and(X,Y)).                    % -|(A ^ B)
nor(X,Y)   :- mnot(or(X,Y)).                     % nor(X,Y)  :- -|(X), -|(Y).
impl(X,Y)  :- or(mnot(X),Y).                     % (A - > B) ==>  (-|A v B)
xor(X,Y)   :- or(and(mnot(X),Y),and(X,mnot(Y))). % (-|A ^ B) v (A ^ -|B)
equiv(X,Y) :- and(or(mnot(X),Y),or(X,mnot(Y))).  % (-|a v b)^ (a v -|b)
reimp(X,Y) :- impl(Y,X).                         % Befragung im AI-Labor

%**************************************************
% Aufgabe 2.2.3)                                  *
% Information zur Hilfsfunktion auswertung/3:     *
% Auswertung(_,_,_) ist wichtig für die Tabelle   *
% dies ist der Bestandteil der Tabelle            *
% hier ist die Tabelle formartiert.               *
% Hier werden die Ausdrücke ebenfalls ausgewertet *
%**************************************************
% EINFÜHRUNG ENDE!                                *
%**************************************************
% Funktion: auswertung/3                          *
% Auswertung für eines bestimmten logischen       *
% Ausdrucks für zwei aussagenlogische Variablen!  *
%**************************************************
auswertung(X,Y,Ausdruck) :-   write(X),                        % Gebe X aus
                              write(' '),                      % Leerzeichen
                              write(Y),                        % Gebe Y aus
                              write(' '),                      % Leerzeichen
                              Ausdruck, !, writeln(' true ' ), % Werte Ausdruck aus, setze Cut (sonst haben wir redundante Fälle) und schreibe true.
                              fail.                            % Setze gezielt Backtracking an
auswertung(_,_,_) :- writeln(' fail ').                        % Wenn auswertung != true (_,_,_), dann ist der Ausdruck (false) fail.

%**************************************
% Aufgabe 2.2.3)                      *
% Funktion: tafel/3                   *
%**************************************
tafel(X, Y, Ausdruck) :- bind(X),                  % Binde die Variable X (s. implementation von bind(X))
                         bind(Y),                  % Binde die Variable X (s. implementation von bind(X))
                         auswertung(X,Y,Ausdruck), % Der Ausdruck wird hierbei an auswertung(..) übermittelt und ausgewertet.
                         fail.                     % Setze gezielt Backtracking an
tafel(_, _, _).                                    % Damit die Abfrage am Ende uns kein ,,false" anzeigt, sagen wir, dass die Funktion beim Aufruf ein true ist.

%************************************************************************
% Hilfsfunktion: auswertung/3                                           *
% Beschreibung: Auswertung für eines                                    *
% bestimmten logischen Ausdrucks für drei(!) aussagenlogische Variablen!*
%************************************************************************
auswertung(X,Y,Z,Ausdruck) :- write(X),                        % Gebe X aus
                              write(' '),                      % Leerzeichen
                              write(Y),                        % Gebe Y aus
                              write(' '),                      % Leerzeichen
                              write(Z),                        % Gebe Z aus
                              Ausdruck, !, writeln(' true ' ), % Werte Ausdruck aus, setze Cut (sonst haben wir redundante Fälle) und schreibe true.
                              fail.                            % Setze gezielt Backtracking an
auswertung(_,_,_,_) :- writeln(' fail ').                      % Wenn auswertung != true (_,_,_), dann fail.

%**************************************
% Aufgabe 2.2.4)                      *
% Funktion: tafel3/4                  *
%**************************************
tafel3(X, Y, Z, Ausdruck) :- bind(X),                    % Binde die Variable X
                             bind(Y),                    % Binde die Variable Y
                             bind(Z),                    % Binde die Variable Y
                             auswertung(X,Y,Z,Ausdruck), % Der Ausdruck wird hierbei an auswertung/3 übermittelt und ausgewertet.
                             fail.                       % Setze gezielt Backtracking an
tafel3(_, _, _, _).                                      % Damit die Abfrage am Ende uns kein ,,false" anzeigt, sagen wir, dass die Funktion beim Aufruf ein true ist.

%**************************************
% Hilfsfunktion: bind/1               *
% Bedchreibung: true oder false       *
%**************************************
bind(true).
bind(false).

%**************************************
% Aufgabe 2.2.5)                      *
% Funktion: tafeln/2                  *
%**************************************
tafeln(List,Ausdruck) :- bindList(List),            % Binde die Liste
                         auswertung(List,Ausdruck), % Auswerte den Ausdruck
                         nl, fail.                  % Setze gezielt Backtracking an
tafeln(_,_).                                        % Damit die Abfrage am Ende uns kein ,,false" anzeigt, sagen wir, dass die Funktion beim Aufruf ein true ist.


%***************************************
% Hilfsfunktion: bindList/1            *
% Wichtig ist, dass wir alle Var.      *
% in der Liste binden müssen!          *
%***************************************
bindList([]).                              % Fall: Wenn Liste mit keinem Element
bindList([K | R]) :- bind(K), bindList(R). % Binde die Liste und den Rest durch Rekursion

%***************************************
% Hier findet die Auswertung statt     *
% von der Liste & dem Ausdruck!        *
%***************************************
auswertung([],_).
auswertung([K | R], Ausdruck) :- write(K),                                  % Gebe den Kopf aus
                                 write(' '),                                % Leerzeichen
                                 write(R),                                  % Gebe Rest aus
                                 Ausdruck, !, write(' = '),write(' true '). % Auswertung
                                 
auswertung(_,_) :- write(' '),  write(' = '), write(' fail ').






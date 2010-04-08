; This is the "Building the Interface" program for the Piccolo2D structured 2D
;  graphics framework. You can find the original Java program at
;
;  http://www.piccolo2d.org/learn/interface.html
;

(ns piccolotest
  (:gen-class)
  (:import
    (java.awt   Color Graphics2D)
    (edu.umd.cs.piccolo   PCamera PCanvas PInputManager PLayer PNode
      POffscreenCanvas PRoot)
    (edu.umd.cs.piccolo.event   PBasicInputEventHandler PDragEventHandler
      PDragSequenceEventHandler PInputEvent PInputEventFilter PPanEventHandler
      PZoomEventHandler)
    (edu.umd.cs.piccolo.nodes   PHtmlView PImage PPath PText)
    (edu.umd.cs.piccolo.util   PAffineTransform PBounds PDebug PDimension
      PObjectOutputStream PPaintContext PPickPath PStack PUtil)
    (edu.umd.cs.piccolox   PApplet PFrame)
    ))

(defn create-interface-frame
  "Creates the main InterfaceFrame used by the program."
  []
  (proxy [PFrame] []
    (initialize []
      (let [aNode (PNode.)
            anotherNode (PNode.)
            layer (.. this getCanvas getLayer)
            image (PImage. (.toImage layer 300 300 nil))]

        (.. this getCanvas (setPanEventHandler nil))
        (.. this getCanvas (addInputEventListener (PDragEventHandler.)))

        (.setBounds aNode 0 0 100 80)
        (.setPaint aNode (Color/RED))
        (.addChild layer aNode)

        (.setBounds anotherNode 0 0 100 80)
        (.setPaint anotherNode (Color/YELLOW))
        (.addChild aNode anotherNode)

        (.setBounds aNode -10 -10 200 110)

        (.translate aNode 100 100)
        (.scale aNode (Float. 1.5))
        (.rotate aNode 45)

        (.addChild layer (PPath/createEllipse 0 0 100 100))
        (.addChild layer (PPath/createRectangle 0 100 100 100))
        (.addChild layer (PText. "Hello World"))

        (let [image (PImage. (.toImage layer 300 300 nil))]
          (.addChild layer image))

        ))))

(defn -main []
  (let [main-frame (create-interface-frame)]
    (.setVisible main-frame true)))

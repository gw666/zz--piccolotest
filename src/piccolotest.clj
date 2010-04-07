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
            node2 (PNode.)
            layer (.. this getCanvas getLayer)]

        (.setBounds aNode 0 0 100 80)
        (.setPaint aNode (Color. 255 0 0))

        (.addChild layer aNode)

        (.. this getCanvas (setPanEventHandler nil))
        (.. this getCanvas (addInputEventListener (PDragEventHandler.)))

        ))))

(defn -main []
  (let [main-frame (create-interface-frame)]
    (.setVisible main-frame true)))

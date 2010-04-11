; This is the "Building the Interface" program for the Piccolo2D structured 2D
;  graphics framework. You can find the original Java program at
;
;  http://www.piccolo2d.org/learn/interface.html
;

(ns piccolotest
  (:gen-class)
  (:import
    (java.awt   Color Graphics2D)
    (edu.umd.cs.piccolo.nodes   PHtmlView PImage PPath PText)
    (edu.umd.cs.piccolo   PCamera PCanvas PInputManager PLayer PNode
      POffscreenCanvas PRoot)
    (edu.umd.cs.piccolo.event   PBasicInputEventHandler PDragEventHandler
      PDragSequenceEventHandler PInputEvent PInputEventFilter PPanEventHandler
      PZoomEventHandler)
    (edu.umd.cs.piccolo.util   PAffineTransform PBounds PDebug PDimension
      PObjectOutputStream PPaintContext PPickPath PStack PUtil)
    (edu.umd.cs.piccolox   PApplet PFrame)))

(defn create-interface-frame
  "Creates the main InterfaceFrame used by the program."
  []

  (defn create-toggle-shape
    "Creates an ellipse that changes color when it is clicked."
    []
    (proxy [PPath] []
      (PPath []
        (.setPathToEllipse this 0 0 100 80)
        (println "Hello world!"))))


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
        (.scale aNode 1.5)
        (.rotate aNode 45)

        (.addChild layer (PPath/createEllipse 0 0 100 100))
        (.addChild layer (PPath/createRectangle 0 100 100 100))
        (.addChild layer (PText. "Hello World"))

        (let [image (PImage. (.toImage layer 300 300 nil))]
          (.addChild layer image))

        (let [myCompositeFace (PPath/createRectangle 0 0 100 80)
              eye1 (PPath/createEllipse 0 0 20 20)
              eye2 (.clone eye1)
              mouth (PPath/createRectangle 0 0 40 20)
              ]
          (.setPaint eye1 (Color/YELLOW))
          (.setPaint eye2 (Color/YELLOW)) ; "let" forces deviation from original
          (.setPaint mouth (Color/BLACK))
          (.addChild myCompositeFace eye1)
          (.addChild myCompositeFace eye2)
          (.addChild myCompositeFace mouth)

          (.setChildrenPickable myCompositeFace false)

          (.translate eye2 25 0)
          (.translate mouth 0 30)

          (let [b (.getUnionOfChildrenBounds myCompositeFace nil)]
            (.inset b -5 -5)
            (.setBounds myCompositeFace b)

            (.scale myCompositeFace 1.5)
            
            (.addChild layer myCompositeFace))

          (let [ts (create-toggle-shape)]
            (.setPaint ts (Color/ORANGE))
            (.addChild layer ts))
          )))))




(defn -main []
  (let [main-frame (create-interface-frame)]
    (.setVisible main-frame true)))

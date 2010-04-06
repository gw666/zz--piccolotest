; This is the canonical Hello World program for the Piccolo2D structured 2D
;  graphics framework. You can find the original Java program at
;
;  http://www.piccolo2d.org/learn/patterns.html
;
;  This program creates a Swing window that displays the phrase "Hello World"
;  as a pNode object. You can:
;
;  * scroll the underlying canvas by holding down the LEFT mouse button while the
;   pointer is anywhere within the window
;
;  * zoom in or zoom out of the underlying canvas by holding down the RIGHT
;    mouse button AND moving the mouse pointer right or left

(ns piccolotest
 (:gen-class)
 (:import
   (edu.umd.cs.piccolo   PCamera PCanvas PInputManager PLayer PNode
     POffscreenCanvas PRoot)
   (edu.umd.cs.piccolo.eventvent   PBasicInputEventHandler PDragEventHandler
      PDragSequenceEventHandler PInputEvent PInputEventFilter PPanEventHandler
      PZoomEventHandler)
   (edu.umd.cs.piccolo.nodes   PHtmlView PImage PPath PText)
   (edu.umd.cs.piccolo.util   PAffineTransform PBounds PDebug PDimension
     PObjectOutputStream PPaintContext PPickPath PStack PUtil
   (edu.umd.cs.piccolox )))

(defn create-frame
  "Creates the main PFrame used by the program."
  []
  (proxy [PFrame] []
    (initialize []
      (let [aNode (PText. "Hello World")]
        (.. this getCanvas getLayer (addChild aNode))))))

(defn -main []
  (let [main-frame (create-frame)]
    (.setVisible main-frame true)))

(ns clojure-noob.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "I'm a little tea pot!"))

(defn testing
  "Just a test function"
  []
  (println "Testing Complete!"))

(defn too-enthusiastic
  "Be waaaay to into someone"
  [name]
  (str "OMG YOU'RE the best " name " EVAR AND WE SHOULD RUN AWAY TOGETHER!!!!!@!"))

(defn add
  "Add n numbers"
  ([a b] (+ a b))
  ([a b c] (+ a b c)))

(defn add2
  [& args]  (eval (conj args '+)))

(defn codger-communication [name]
  (println (str  "Get off my lawn " name "!!!")))

(defn codger [& names]
  (map codger-communication names))

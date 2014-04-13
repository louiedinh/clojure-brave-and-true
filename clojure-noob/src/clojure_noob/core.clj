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

(defn my-first [[first-thing]] first-thing)

(defn all-the-things [[ first-thing & rest-things]] rest-things)

(defn lat-lng [{lat :lat lng :lng}] (list lat lng))

(defn lat-lng-keys [{:keys [lat lng]}]
  (println (str  "The treature's at (" lat ", " lng ")")))


(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])

(defn has-matching-part?
  [part]
  (re-find #"^left-" (:name part)))

(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"left-" "right-") :size (:size part)})

(defn symmetrize
  [asym-parts]
  (loop [remaining-parts asym-parts
         final-parts []]
    (if (empty? remaining-parts)
      final-parts
      (let [[part & remaining] remaining-parts
            final-parts (conj final-parts part)]
        (if (has-matching-part? part)
          (recur remaining (conj final-parts (matching-part part)))
          (recur remaining final-parts))))))

(defn my-reduce
  [f init col]
  (loop [remaining-col col result init]
    (if (empty? remaining-col)
      result
      (let [[first & rest] remaining-col]
        (recur rest (f result first))))))

(defn reduce-symmeterize [asym-body-parts]
  (reduce (fn [final-parts part]
            (let [final-parts (conj final-parts part)]
              (if (has-matching-part? part)
                (conj final-parts (matching-part part))
                final-parts)))
          []
          asym-body-parts))

(defn hit
  [asym-body-parts]
  (let [sym-body-parts (symmetrize asym-body-parts)
        total-body-size (reduce + (map :size sym-body-parts))
        target (inc (rand total-body-size))]
    (loop [[part & rest-parts] sym-body-parts
           accumulated-size 0]
      (if (> accumulated-size target)
        part
        (recur rest-parts (+ accumulated-size (:size part)))))))


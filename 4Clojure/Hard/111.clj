(fn [x s]
  (let [l (fn [s] (->> s (re-seq #"[^\s]") (map first) (map #(or ({\# \| \_ \.} %) %))))
        s (map l s)
        v (apply map vector s)
        f (fn [x] (->> x (map #(apply str %)) (interpose "|") (apply str)))]
    (boolean (re-matches (re-pattern (str "(?i)" (f s) "|" (f v))) x))))

(fn shite [ss]
  (let [[union difference] [clojure.set/union clojure.set/difference]
        ss (if (= (count (first ss)) 3)
              (for [x ss d '[D d]] (conj x d)) ss)
        terms (set (map (fn [s] (reduce #(+ % (or ('{A 8 B 4 C 2 D 1} %2) 0)) 0 s)) ss))
        I [0 1 3 2]
        a (for [i I] (for [j I] (+ (* 4 j) i)))
        A (#(concat % %) (map concat a a))
        get-sq (fn [r c m n]
                  (mapcat #(->> % (drop c) (take n)) (->> A (drop r) (take m))))
        M (let [flt (partial filter #(every? terms %))
                f (fn [[nr nc]]
                    (let [rng #(if (= % 4) [0] (range 4))]
                      (for [r (rng nr) c (rng nc)] (get-sq r c nr nc))))
                sq (flt (mapcat f [[2 4] [4 2] [1 4] [4 1] [2 2] [1 2] [2 1]]))]
            (assoc (group-by count sq) 1 (mapv list terms)))
        m (loop [chart (zipmap (range 16) (repeat #{}))
                  [[n sqs] & m_] (reverse (sort M))]
            (if (nil? n) chart
              (recur (reduce #(merge-with conj % %2) chart
                        (for [sq sqs x sq :when (empty? (chart x))] {x sq})) m_)))
        fq (frequencies (mapcat concat (apply union (vals m))))
        ess (for [[k v] fq :when (= v 1)] k)
        !ess (for [[k v] fq :when (> v 1)] k)
        f #(apply union (map m %))
        r (difference (set (apply concat (f !ess))) (set (apply concat (f ess))))
        r (difference (set (map m r)) (f !ess))
        g (fn [m]
            (let [covered? #(every? (set (apply concat %)) !ess)]
              (loop [res #{m}
                     [m0 & m_] [m]]
                (if (nil? m0) res
                  (let [m's (for [i m0 :let [m-i (disj m0 i)]
                              :when (and (not (res m-i)) (covered? m-i))] m-i)]
                    (recur (into res m's) (into m_ m's)))))))
        to-expr 
          (fn [sqs]
            (let [ind '[#{0} #{1} #{3} #{2} #{0 1} #{1 3} #{3 2} #{2 0}]
                  ab (zipmap ind '[(a b) (a B) (A B) (A b) (a) (B) (A) (b)])
                  cd (zipmap ind '[(c d) (c D) (C D) (C d) (c) (D) (C) (d)])
                  get-ind #((juxt quot mod) % 4)
                  sq->expr (fn [sq] (#(set (concat (ab (first %)) (cd (second %)))) 
                              (apply map (partial conj #{}) (map get-ind sq))))]
              (set (map sq->expr sqs))))]
    (to-expr (union (apply min-key count (g r)) (f ess)))))

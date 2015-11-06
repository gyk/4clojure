;; Difficulty: Easy

;#19
(comp first reverse)

;#20
(comp second reverse)

;#21
#(->> %1 (drop %2) first)

;#22
#(loop [c 0 xs %]
  (if (empty? xs) c (recur (inc c) (rest xs))))

;#23
reduce #(cons %2 %1) ()

;#24
apply +

;#25
filter odd?

;#26
#(take % ((fn f [x y]
            (cons x (lazy-seq (f y (+ x y))))) 1 1))

;#27
#(= (seq %) (reverse %))

;#28
(fn f [x & xs]
  (if (coll? x)
    (concat (f x) (f xs))
    (cons x (f xs)))
  (if (empty? xs)))

;#29
#(apply str (clojure.string/split % #"[^A-Z]"))

;#30
(->> % (partition-by identity) (mapcat first))

;#31
partition-by identity

;#32
mapcat (partial repeat 2)

;#33
#(mapcat (partial repeat %2) %1)

;#34
#(take (- %2 %) (iterate inc %))

;#38
(fn [& xs] (reduce #(if (> %1 %2) %1 %2) xs))

;#39
mapcat vector

;#40
#(rest (mapcat vector (repeat %1) %2))

;#41
#(->> % (partition %2 %2 [nil]) (mapcat butlast))

;#42
#(apply * (range 1 (inc %)))

;#45
[1 4 7 10 13]

;#47
4

;#48
6

;#49
(juxt take drop)

;#51
[1 2 3 4 5]

;#61
#(into {} (map vector %1 %2))

;#62
(fn iter [f i]
  (cons i (lazy-seq (iter f (f i)))))

;#63
(fn [f xs] (reduce #(update-in % [(f %2)] (fnil conj []) %2) {} xs))

;#66
(fn [a b]
  (if (= 0 b) a (recur b (rem a b))))

;#81
(comp set filter)

;#83
(fn [& bs]
  (every? true? [(some true? bs) (some false? bs)]))

;#88
#(set (into (remove %1 %2) (remove %2 %1)))

;#90
#(set (for [x %1 y %2] [x y]))

;#95
(fn tree? [t]
  (or (nil? t)
      (and (coll? t)
           (= (count t) 3)
           (let [[root l-child r-child] t]
                (and (not (coll? root))
                     (tree? l-child)
                     (tree? r-child))))))

;#96
(fn [[_ l r]]
  ((fn m? [[l_ ll lr] [r_ rl rr]]
    (if (= nil l_ r_)
      true
      (and (= l_ r_)
           (m? ll rr)
           (m? lr rl))))
    l r))

;#97
(fn [i]
  (nth (iterate #(mapv + (conj % 0) (cons 0 %)) [1]) (dec i)))

;#99
#(->> (* %1 %2) str seq (map (comp read-string str)))

;#100
(fn [& xs]
  (let [gcd #(if (= 0 %2) %1 (recur %2 (mod %1 %2)))
        lcm #(/ (* %1 %2) (gcd %1 %2))]
    (reduce lcm xs)))

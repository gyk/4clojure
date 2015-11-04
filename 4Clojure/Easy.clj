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

;#43
#(apply map vector (partition %2 %))

;#44
#(let [k (mod % (count %2))]
  (concat (drop k %2) (take k %2)))

;#45
[1 4 7 10 13]

;#46
#(fn [x y] (% y x))

;#47
4

;#48
6

;#49
(juxt take drop)

;#50
#(vals (group-by class %))

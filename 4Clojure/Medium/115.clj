#(let [s (->> % str (map int) vec)
       n (count s)
       l (subvec s 0 (/ n 2))
       r (subvec s (-> n inc (/ 2)) n)]
  (= (apply + l) (apply + r)))

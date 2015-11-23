(fn [x b]
  (loop [x x, out ()]
    (if (zero? x)
      (if (empty? out) [0] out)
      (recur (quot x b) (conj out (mod x b))))))

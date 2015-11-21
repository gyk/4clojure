(fn prime [k]
  (let [estimate-prime  ; http://stackoverflow.com/a/25440642
          (fn [n]
            (let [log-n (Math/log n)
                  log-log-n (Math/log log-n)
                  upper
                    (cond
                      (< n 6)
                        ([0 2 3 5 7 11] n)
                      (>= n 688383)
                        (* n (+ log-n log-log-n -1.0 (/ (- log-log-n 2.00) log-n)))
                      (>= n 178974)
                        (* n (+ log-n log-log-n -1.0 (/ (- log-log-n 1.95) log-n)))
                      (>= n 39017)
                        (* n (+ log-n log-log-n -0.9484))
                      :else
                        (* n (+ log-n (* 0.6 log-log-n))))]
                  (Math/ceil upper)))
        n (estimate-prime k)
        candi (range 2 (inc n))
        div-by (fn [divisor]
                   #(= (mod % divisor) 0))]
    (loop [curr k
           remain candi
           out []]
      (let [head (first remain)
            new-out (conj out head)]
        (if (= curr 1)
            new-out
            (recur (dec curr) (doall (remove (div-by head) (rest remain))) new-out))))))
; FIXME: It's actually slower than some seemingly naive solution.

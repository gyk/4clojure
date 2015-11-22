(fn [f & args]
  (loop [ret (apply f args)]
    (if (fn? ret) (recur (ret)) ret)))

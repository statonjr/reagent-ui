(ns reagent-ui.prod
  (:require [reagent-ui.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)

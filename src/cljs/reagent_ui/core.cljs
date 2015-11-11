(ns reagent-ui.core
    (:require [reagent.core :as reagent :refer [atom]]
              [reagent.session :as session]
              [secretary.core :as secretary :include-macros true]
              [goog.events :as events]
              [ui.button :as button]
              [goog.history.EventType :as EventType])
    (:import goog.History))

(def menu-data
  [{:label "Google", :path "google.com", :action #(println "Google")},
  {:label "Yahoo!", :path "yahoo.com", :action #(println "Yahoo!")},
  {:label "Youtube", :path "youtube.com", :action #(println "Youtube")}])

(defn home-page []
  [:div
    [:h2 "Basic Buttons"]
    [:div [button/basic "Basic Button" "custom-class(es)-here and-here" #(println "I work")]]
    [:div [button/submit "Submit Button" "custom-class(es)-here and-here" #(println "I work")]]
    [:div [button/dropdown "Dropdown Button" "custom-class(es)-here and-here" menu-data]]
    ])

(defn about-page []
  [:div [:h2 "About reagent-ui"]
   [:div [:a {:href "#/"} "go to the home page"]]])

(defn current-page []
  [:div [(session/get :current-page)]])

;; -------------------------
;; Routes
(secretary/set-config! :prefix "#")

(secretary/defroute "/" []
  (session/put! :current-page #'home-page))

(secretary/defroute "/about" []
  (session/put! :current-page #'about-page))

;; -------------------------
;; History
;; must be called after routes have been defined
(defn hook-browser-navigation! []
  (doto (History.)
    (events/listen
     EventType/NAVIGATE
     (fn [event]
       (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

;; -------------------------
;; Initialize app
(defn mount-root []
  (reagent/render [current-page] (.getElementById js/document "app")))

(defn init! []
  (hook-browser-navigation!)
  (mount-root))

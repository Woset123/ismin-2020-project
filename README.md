# Ismin 3A Android Project
# Population Cities
Bicheler Hugo & Dall'Agnol Eric

## Table of Contents

- [Context](#context)
- [API](#api)
- [Android](#android)
  - [Layout](#layout)
  - [Features](#features)
  - [Issues](#issues)







-------

## Context

This application  with its Web Server were developed as part of ISMIN Android courses.

The main goal is to display data on an Android App using our own API.

This project shows the top 20 most populated Towns in these countries : 
- France, 
- UK, 
- Spain, 
- Switzerland, 
- Belgium, 
- Germany
- Italy


The App is connected to a remote REST API server thanks to clervercould.com for free access to their resources ! Data are stored into the remote server in JSON format


***NOTE : Data have been refactored but come from this [database](https://data.opendatasoft.com/explore/dataset/worldcitiespop%40public/table/?disjunctive.country&timezone=Europe%2FBerlin&sort=population&dataChart=eyJxdWVyaWVzIjpbeyJjaGFydHMiOlt7InR5cGUiOiJsaW5lIiwiZnVuYyI6Ik1BWCIsInlBeGlzIjoicG9wdWxhdGlvbiIsInNjaWVudGlmaWNEaXNwbGF5Ijp0cnVlLCJjb2xvciI6IiMyQzNGNTYifV0sInhBeGlzIjoiY291bnRyeSIsIm1heHBvaW50cyI6MjAwLCJzb3J0IjoiIiwiY29uZmlnIjp7ImRhdGFzZXQiOiJ3b3JsZGNpdGllc3BvcEBwdWJsaWMiLCJvcHRpb25zIjp7ImRpc2p1bmN0aXZlLmNvdW50cnkiOnRydWUsInRpbWV6b25lIjoiRXVyb3BlL0JlcmxpbiJ9fX1dLCJ0aW1lc2NhbGUiOiIiLCJkaXNwbGF5TGVnZW5kIjp0cnVlLCJhbGlnbk1vbnRoIjp0cnVlfQ%3D%3D)***

-------

## API

This REST API is deployed on clervercloud under the name "TownsApp" and can be reach using this [URL](https://towns-app.cleverapps.io/)

-------

## Android



### Layout

To carry out this development, here are the differents files :

#### Activities

- Intro : launcher activity
- MainActivity : shows a webview relative to most populated towns in Europe
- TownListActivity : displays all the towns from the database within two tabs
- CreateTownActivty : displays view to create a new town
- FocusTownActivity : displays the town view when click on its cardview on the list

#### Fragments

- TownListFragment : First tab of TownListActivity : displays non-favorite towns
- FavoritesFragment : Second tab of TownListActivity : displays favorite towns

#### Adapters

- TownAdapter : handles the list of the Towns
- MyPagerAdapter : handles the tabs layout

#### Miscellaneous

- Town : Town data class (City, Country, Population)
- TownList : HashMap of Towns
- TownViewHolder : handles towns view
- IRequests : interface to handle requests to CleverCloud using Retrofit


### Features

- Splash Screen :heavy_check_mark:
- Overview of the 20 most populated cities of some European countries :heavy_check_mark:
- Add a new Town :heavy_check_mark:
  - On the tab "Towns" click on the floating button "+"
- Remove an existing Town :heavy_check_mark:
  - Long click on the item to remove it. You can only remove an item in the tab Towns ! However if a remove a Town saved also as a favorite, this Town will also be removed from the favorites as well
- Favorite Towns can be handled :heavy_check_mark:
  - Short click on an item (no matter the tab) to display a new view where you can click on the heart to add/remove to/from the favorites
- A non elegant but functional refresh !
  - Click on the floating button refresh displayed on the two tabs
- A lot lot lot of time of researches :relieved:

### Issues

- I tried to implement a SearchView widget but I cannot find an alternative way of using an ArrayAdapter.
- Difficulty to "refresh" my tab fragments using the "MyPagerAdapter" -> alternative was to restart the activity (to reload the data) to refresh but not very elegant...


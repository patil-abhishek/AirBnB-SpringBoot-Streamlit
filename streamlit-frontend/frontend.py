import streamlit as st
import streamlit.components.v1 
import st_leaflet as stl

stl.set_config(api_key="YOUR_MAPBOX_API_KEY")

# Set default location
default_location = (37.7749, -122.4194) # San Francisco

# Get user input for location
location = st.text_input("Enter a location in latitude, longitude format:", f"{default_location[0]}, {default_location[1]}")

# Parse user input into latitude and longitude
try:
    latitude, longitude = location.split(",")
    latitude, longitude = float(latitude.strip()), float(longitude.strip())
except ValueError:
    st.error("Please enter a valid latitude, longitude format")
    st.stop()

# Display map centered on specified location
stl_static_map = stl.build_static_map((latitude, longitude), zoom=12)
st.image(stl_static_map)

# Add marker at specified location
stl_marker_layer = stl.build_marker_layer([(latitude, longitude)])
stl_static_map.add_layer(stl_marker_layer)

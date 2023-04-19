import streamlit as st
import requests

st.set_page_config(layout="wide")

# Define a function to show the reviews for a property
def show_reviews(property_id):
    with st.expander("Reviews"):
        # TODO: fetch and display reviews for this property
        st.write("TODO: show reviews for property", property_id)

        # Form to add a new review
        st.write("Add a review:")
        review_text = st.text_input("Enter your review", key=str(property_id)+"reviewstext")
        rating = st.slider("Enter a rating (1-5)", 1, 5, key=str(property_id)+"reviewslider")
        if st.button("Submit review", key=str(property_id)+"reviewsubmit"):
            # TODO: add the review to the database
            st.write("TODO: add review to database")


def main():
    # st.title("Sasta AirBnB")
    st.subheader("Search Properties")
    
    col1, col2 = st.columns(2,  gap = "large")
    with col1:
        city = st.text_input('City')
    with col2:
        address = st.text_input('Address')

    col3, col4, col5, col6 = st.columns([1,1,3,2], gap = "medium")
    with col3:
        bedrooms = st.number_input('Bedrooms', step = 1, value = 0, min_value=0)
    with col4:
        price = st.number_input('Price', min_value=0, max_value=100000, value=0, step=1000)
    with col6:
        averageRating = st.slider('Average Rating', min_value=0, max_value=5, step=1)
    with col5:
        options = st.multiselect(
                'Facilities',
                ['Parking', 'Swimming Pool'])

    # Create a dictionary with the input data
    data = {
        'city': city,
        'address': address,
        'bedrooms': bedrooms,
        'price': price,
        'swimmingPool': ('Swimming Pool' in options),
        'parking': ('Parking' in options),
        'averageRating': averageRating
    }

    if 'properties' not in st.session_state:
        st.session_state.properties = {}

    if st.button("Search"):
        if (st.session_state.properties=={}):
            st.session_state.properties = requests.get(url = 'http://localhost:8080/api/guest/searchProperty', params = data).json()

    if st.button("Clear results"):
        st.session_state.properties = {}

    for property in st.session_state.properties:
        st.session_state.currentProperty = property['property_id']
        st.write(property['propertyName'])
        st.write(property["city"])
        st.write(property["address"])
        st.write(property["bedrooms"], "bedrooms")
        st.write(property["area"], "sq. ft.")
        st.write("Swimming pool:", "Yes" if property["swimmingPool"] else "No")
        st.write("Parking:", "Yes" if property["parking"] else "No")
        st.write("Price:", property["price"])
        st.write("Host:", property["hostName"])
        st.write("Host phone:", property["hostPhone"])
        st.write("Host email:", property["hostEmail"])

        # Buttons to book or add to favorites
        col1, col2 = st.columns(2)
        if col1.button("Book", key=str(property["property_id"])+"book"):
            # TODO: implement booking logic
            st.write("TODO: implement booking logic for property", property["property_id"])
        if col2.button("Add to favorites", key=str(property["property_id"])+"fav"):
            # TODO: implement add to favorites logic
            st.write("TODO: implement add to favorites logic for property", property["property_id"])

        if 'currentProperty' in st.session_state:
            show_reviews(property["property_id"])

    
if __name__ == '__main__':
    main()
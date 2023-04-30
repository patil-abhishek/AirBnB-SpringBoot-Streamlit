import streamlit as st
import requests
import json

def main():
    st.subheader("Bookings History:")
    if(st.session_state.cuser==False):
        st.warning("You haven't logged in!")
    else:
        response = requests.get(url = 'http://localhost:8080/api/user/getBookings').json()
        st.write(response)
    # for booking in response:
        
if __name__ == "__main__":
    main()
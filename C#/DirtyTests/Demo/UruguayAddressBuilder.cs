using DirtyTestsWorkshop.Demo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DirtyTests.Demo
{
    public class UruguayAddressBuilder
    {
		private string city = "";
		private string street = "";
		private int num = 0;

		public UruguayAddressBuilder inCity(string city) 
		{
			this.city = city;
			return this;
		}

		public UruguayAddressBuilder on(String street)
		{
			this.street = street;
			return this;
		}

		public UruguayAddressBuilder number(int number)
		{
			this.num = number;
			return this;
		}

		public Address build()
		{
			Address address = new Address();
			address.country = "Uruguay";
			address.city = city;
			address.street = street;
			address.number = num;
			return address;
		}

}
}

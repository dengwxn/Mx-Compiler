int n;
int tot;
int[] flag;
int[] pr;
int[] phi;
int N;

void init()
{
	N = 1000000;
	flag = new int[N];
	pr = new int[N];
	phi = new int[N];
}

int min(int a, int b)
{
	if (a < b)
		return a;
	return b;
}

int Calc(int n)
{
	int res = 0;
	int l = 1;
	for (; l <= n; ++l)
	{
		int r = min(n, n / (n / l));
		res = res + (n / l) * (r - l + 1);
		l = r;
	}
	return res;
}

int main()
{
	init();
	n = 1000000;
	phi[1] = 1;
	int i = 2;
	for (; i < N; ++i)
	{
		if (flag[i] == 0)
		{
			pr[++tot] = i;
			phi[i] = i - 1;
		}
		int j = 1;
		for (; j <= tot; ++j)
		{
			int k = pr[j];
			if (i * k >= N)
				break;
			flag[i * k] = k;
			if (i % k == 0)
			{
				phi[i * k] = phi[i] * k;
				break;
			}
			else
				phi[i * k] = phi[i] * phi[k];
		}
	}
	int ans = 0;
	int d = 1;
	for (; d * d <= n; ++d)
		ans = ans + phi[d] * Calc(n / d / d);
	print(toString(ans));

	return 0;
}

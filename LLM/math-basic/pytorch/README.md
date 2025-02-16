 # PyTorch 개요 및 사용법

---
## 1. PyTorch란?
PyTorch는 Facebook AI Research(FAIR)에서 개발한 오픈 소스 머신 러닝 프레임워크로, 동적 계산 그래프 및 직관적인 API를 제공하여 연구 및 프로덕션 환경에서 널리 사용됩니다.

### 특징
- **동적 계산 그래프(Dynamic Computation Graphs)**: 데이터 크기나 네트워크 구조 변경이 자유로움
- **GPU 가속 지원**: CUDA를 활용한 빠른 연산 가능
- **Pythonic API**: 직관적이고 사용하기 쉬운 Python 기반 API
- **TorchScript 지원**: 연구에서 프로덕션으로 쉽게 전환 가능
- **풍부한 커뮤니티와 문서 지원**: 방대한 튜토리얼과 예제 코드 제공

---
## 2. 설치 방법
PyTorch는 다양한 환경에서 설치할 수 있으며, 공식 사이트([PyTorch 공식 홈페이지](https://pytorch.org/get-started/))에서 설치 명령어를 확인할 수 있습니다.

### 기본 설치 (CPU 버전)
```sh
pip install torch torchvision torchaudio
```

### GPU 버전 설치 (CUDA 11.8 기준)
```sh
pip install torch torchvision torchaudio --index-url https://download.pytorch.org/whl/cu118
```
---
## 3. PyTorch 구성 요소
PyTorch는 여러 핵심 모듈로 구성됩니다. 주요 구성 요소를 살펴보겠습니다.

### 3.1 Tensor
Tensor는 PyTorch의 기본 데이터 구조로, 다차원 배열을 표현하는 객체입니다. NumPy의 배열과 유사하지만 GPU 가속을 지원합니다.
```python
import torch

tensor = torch.tensor([[1, 2], [3, 4]])
print(tensor)
```

#### Tensor 생성 방법
```python
x = torch.empty(5, 3)  # 초기화되지 않은 텐서
x = torch.rand(5, 3)   # 랜덤 값으로 초기화
x = torch.zeros(5, 3)  # 0으로 초기화
x = torch.ones(5, 3)   # 1로 초기화
x = torch.tensor([5.5, 3.0])  # 직접 값 할당
```

#### Tensor 연산
```python
x = torch.rand(3, 3)
y = torch.rand(3, 3)
z = x + y  # 텐서 덧셈
z = torch.add(x, y)  # 동일한 연산
print(z)
```

### 3.2 Autograd (자동 미분)
PyTorch는 자동 미분을 지원하여 신경망 학습 시 편리하게 기울기를 계산할 수 있습니다.
```python
x = torch.tensor(2.0, requires_grad=True)
y = x ** 2

y.backward()  # dy/dx 계산
print(x.grad)  # tensor(4.)
```

### 3.3 Neural Network (`torch.nn`)
`torch.nn` 모듈은 신경망을 구성하는 기본적인 빌딩 블록을 제공합니다.
```python
import torch.nn as nn

class SimpleModel(nn.Module):
    def __init__(self):
        super(SimpleModel, self).__init__()
        self.linear = nn.Linear(1, 1)
    
    def forward(self, x):
        return self.linear(x)

model = SimpleModel()
```

### 3.4 Optimizer (`torch.optim`)
`torch.optim` 모듈은 다양한 최적화 알고리즘(SGD, Adam 등)을 제공합니다.
```python
import torch.optim as optim

optimizer = optim.SGD(model.parameters(), lr=0.01)
```
---

## 4. 신경망 모델 학습
### 4.1 손실 함수 및 옵티마이저 설정
```python
criterion = nn.MSELoss()  # 평균 제곱 오차 (MSE)
optimizer = optim.SGD(model.parameters(), lr=0.01)
```

### 4.2 학습 루프
```python
x_train = torch.tensor([[1.0], [2.0], [3.0]], requires_grad=True)
y_train = torch.tensor([[2.0], [4.0], [6.0]])

for epoch in range(100):
    optimizer.zero_grad()
    output = model(x_train)
    loss = criterion(output, y_train)
    loss.backward()
    optimizer.step()
    if epoch % 10 == 0:
        print(f'Epoch {epoch}: Loss = {loss.item()}')
```
---

## 5. 데이터 로딩 및 처리
PyTorch에서는 `torch.utils.data.Dataset`과 `DataLoader`를 사용하여 데이터를 쉽게 관리할 수 있습니다.

```python
from torch.utils.data import Dataset, DataLoader

class CustomDataset(Dataset):
    def __init__(self):
        self.data = torch.arange(10).float().view(-1, 1)
    
    def __len__(self):
        return len(self.data)
    
    def __getitem__(self, idx):
        return self.data[idx]

dataset = CustomDataset()
dataloader = DataLoader(dataset, batch_size=2, shuffle=True)

for batch in dataloader:
    print(batch)
```
---

## 6. 모델 저장 및 불러오기
```python
# 모델 저장
torch.save(model.state_dict(), "model.pth")

# 모델 불러오기
model.load_state_dict(torch.load("model.pth"))
```
---

## 7. GPU 사용
PyTorch는 GPU 가속을 지원하며 `to(device)`를 사용하여 텐서 및 모델을 GPU로 이동할 수 있습니다.
```python
device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
model.to(device)
x_train = x_train.to(device)
y_train = y_train.to(device)
```
---

## 8. 추가 학습 자료
- [PyTorch 공식 문서](https://pytorch.org/docs/stable/index.html)
- [PyTorch 튜토리얼](https://pytorch.org/tutorials/)
- [PyTorch GitHub](https://github.com/pytorch/pytorch)

---

## 9. 결론
PyTorch는 연구 및 실무에서 강력한 성능을 제공하는 딥러닝 프레임워크입니다. 직관적인 API, 자동 미분 기능, GPU 가속 지원 등으로 인해 사용하기 편리하며, 연구 및 프로덕션 환경 모두에 적합합니다. 앞으로 다양한 PyTorch 기능을 활용하여 딥러닝 모델을 개발해 보세요!

